package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.User;
import com.gestion.stage.dao.UserDao;
import com.gestion.stage.service.facade.FileStorageService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FieldsUtil;
import com.gestion.stage.utils.FileUtil;
import com.gestion.stage.utils.ResponseMessage;
import com.google.common.net.HttpHeaders;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<User> findByDateNaissanceGreaterThan(Date dateNaissance) {
		return userDao.findByDateNaissanceGreaterThan(dateNaissance);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public List<User> findByNomContains(String nom) {
		return userDao.findByNomContains(nom);
	}

	@Override
	public List<User> findByPrenomContains(String prenom) {
		return userDao.findByPrenomContains(prenom);
	}

	@Override
	public List<User> findByDateJoin(Date dateJoin) {
		return findByDateJoin(dateJoin);
	}

	@Override
	public int login(User user) {
		User foundedutilisateur = findByUsername(user.getUsername());
		if(foundedutilisateur == null) {
			return -1;
		} else if (!foundedutilisateur.getPassword().equals(user.getPassword())) {
			return -2;
		} else {
			return 1;
		}
	}

	@Override
	public int register(User user) {
		User foundedUtilisateur = findByUsername(user.getUsername());
		if (foundedUtilisateur != null) {
			return -1;
		} else if (user.getPassword() == "" || user.getPassword() == null) {
			return -2;
		} else if (user.getNom() == "" || user.getNom() == null || user.getPrenom() == "" || user.getPrenom() == null) {
			return -3;
		} else {
			user.setDateJoin(DateUtil.getDate());
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userDao.save(user);
			return 1;
		}
	}

	@Override
	public int update(User user) {
		User foundeduser = findByUsername(user.getUsername());
		if (FieldsUtil.utilisateurFields(user) < 0) {
			return -1;
		} else if (!foundeduser.getReference().equals(user.getReference())) {
			return -2;
		} else {
			userDao.save(user);
			return 1;
		}
	}

	@Transactional
	@Override
	public int removeById(Long id) {
		User user = userDao.findById(id).get();
		if (user == null) {
			return 1;
		} else {
			userDao.delete(user);
			return 1;
		}
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public int save(User user) {
		User u = findByReference(user.getReference());
		if (u != null) {
			return -1;
		} else if (user.getNom() == "" || user.getNom() == null || user.getPrenom() == "" || user.getPrenom() == null) {
			return -2;
		} else {
			user.setDateJoin(DateUtil.getDate());
			userDao.save(user);
			return 1;
		}
	}

	@Override
	public User findByReference(String reference) {
		return userDao.findByReference(reference);
	}

	@Override
	public ResponseEntity<ResponseMessage> uploadProfilePic(String ref, MultipartFile file) {
		String message = "";
		User u = findByReference(ref);
		if (u == null) {
			message += "utilisateur n'existe pas";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		} else {
			MultipartFile fileToStore = FileUtil.getNewFile(FileUtil.getEditedName(file), file);
			try {
				message = "la photo : " + fileToStore.getOriginalFilename() + " enregister avec succée!";
				fileStorageService.save(fileToStore);
				u.setPhoto(fileToStore.getOriginalFilename());
				userDao.save(u);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "on ne peut pas uploader la photo: " + fileToStore.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

	}

	@Override
	public ResponseEntity<Resource> loadImage(String filename) {
		Resource file = fileStorageService.loadPics(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@Override
	public int countusers() {
		return userDao.countUsers();
	}

	@Override
	public Page<User> findAllWithPagination(int page, int size, String sort) {
		if (sort.equals("asc")) {
			return userDao.findAll(PageRequest.of(page, size, Sort.by(Direction.ASC, "id")));
		} else if (sort.equals("desc")) {
			return userDao.findAll(PageRequest.of(page, size, Sort.by(Direction.DESC, "id")));
		} else if (sort.equals("nom")) {
			return userDao.findAll(PageRequest.of(page, size, Sort.by(Direction.ASC, "nom")));
		} else {
			return null;
		}
	}

	@Override
	public ResponseEntity<List<User>> searchForUsers(Specification<User> spec) {
		return new ResponseEntity<>(userDao.findAll(Specification.where(spec)), HttpStatus.OK);
	}

	

}
