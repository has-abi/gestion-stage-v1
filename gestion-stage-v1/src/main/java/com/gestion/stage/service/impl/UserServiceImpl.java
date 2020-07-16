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
import com.gestion.stage.service.facade.EtudiantService;
import com.gestion.stage.service.facade.FileStorageService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.CodeSession;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FileUtil;
import com.gestion.stage.utils.LoginUser;
import com.gestion.stage.utils.ResponseMessage;
import com.google.common.net.HttpHeaders;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private EtudiantService etudiantService;

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
		if (foundedutilisateur == null) {
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
		if (user.getNom() == null || user.getNom() == "" || user.getPrenom() == null || user.getPrenom() == ""
				|| user.getUsername() == null || user.getUsername() == "") {
			return -1;
		} else {
			if (user.getPassword() == null || user.getPassword() == "") {
				user.setPassword(foundeduser.getPassword());
			} else {
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			}
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

	@Override
	public int confirmUser(String code, String cne) {
		User foundedUser = etudiantService.findByCin(cne).getUser();
		if (foundedUser == null) {
			return -1;
		} else if (foundedUser.isConfirm() == true) {
			return -2;
		} else if (!foundedUser.getCodeConfirm().equals(code)) {
			return -3;
		} else {
			foundedUser.setCodeConfirm(null);
			;
			foundedUser.setConfirm(true);
			userDao.save(foundedUser);
			return 1;
		}
	}

	@Override
	public int newUser(LoginUser user) {
		User foundedUser = etudiantService.findByCin(user.getCne()).getUser();
		if (foundedUser == null) {
			return -1;
		} else {
			foundedUser.setUsername(user.getUsername());
			foundedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userDao.save(foundedUser);
			return 1;
		}
	}

	@Override
	public int checkPassword(String ref, String pwd) {
		User foundedUser = findByReference(ref);
		if (foundedUser == null) {
			return -1;
		} else if (!bCryptPasswordEncoder.matches(pwd, foundedUser.getPassword())) {
			return -2;
		} else {
			return 1;
		}
	}

	@Override
	public int checkSecurityQuestion(String username, String question, String reponse) {
		User foundedUser = findByUsername(username);
		if (foundedUser == null) {
			return -1;
		} else if (foundedUser.getQuestion() == null || foundedUser.getReponce() == null) {
			return -4;
		} else if (!foundedUser.getQuestion().equals(question)) {
			return -2;
		} else if (!foundedUser.getReponce().equals(reponse)) {
			return -3;
		} else {
			return 1;
		}
	}

	@Override
	public int updatePassword(String username, String pwd) {
		User foundedUser = findByUsername(username);
		if (foundedUser == null) {
			return -1;
		} else {
			foundedUser.setPassword(bCryptPasswordEncoder.encode(pwd));
			userDao.save(foundedUser);
			return 1;
		}
	}

	@Override
	public int checkCode(String username, String code) {
		User foundedUser = findByUsername(username);
		if (username == null) {
			return -1;
		} else if (CodeSession.getCodeSession(username) == null) {
			return -2;
		} else if (CodeSession.getCodeSession(username).getDuration() < DateUtil.getDate().getTime()) {
			return -3;
		} else if (!CodeSession.getCodeSession(username).getCode().equals(code)) {
			return -4;
		} else {
			return 1;
		}
	}
}
