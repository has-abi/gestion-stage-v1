package com.gestion.stage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Encadreur;
import com.gestion.stage.bean.Role;
import com.gestion.stage.bean.User;
import com.gestion.stage.dao.EncadreurDao;
import com.gestion.stage.service.facade.EncadreurService;
import com.gestion.stage.service.facade.RoleService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FieldsUtil;
@Service
public class EncadreurServiceImpl implements EncadreurService{
	
	@Autowired
	private EncadreurDao encadreurDao;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Override
	public Page<Encadreur> findByProfession(String profession,int page,int size) {
		return encadreurDao.findByProfession(profession,PageRequest.of(page, size));
	}

	@Override
	public Page<Encadreur> findAll(int page,int size,String sort) {
		if(sort.equals("asc")) {
			return encadreurDao.findAll(PageRequest.of(page, size,Sort.by(Direction.ASC,"id")));
		}else if(sort.equals("desc")) {
			return encadreurDao.findAll(PageRequest.of(page, size,Sort.by(Direction.DESC,"id")));
		}else {
			return null;
		}
		
	}

	@Override
	public int save(Encadreur encadreur) {
		Encadreur foundedencadreur = findByReference(encadreur.getReference());
		if(foundedencadreur != null) {
			return -1;
		}else if(FieldsUtil.encadreurFields(encadreur)<0) {
			return -2;
		}else {
			List<Role> roles = new ArrayList<Role>();
			encadreur.getUser().setRoles(roles);
			encadreur.getUser().setReference("u" + DateUtil.getDate().getTime());
			encadreur.getUser().getRoles().add(roleService.getEncadreurRole());
			if(userService.register(encadreur.getUser())<0) {
				return -3;
			}
			encadreur.setUser(userService.findByReference(encadreur.getUser().getReference()));
			encadreurDao.save(encadreur);
			return 1;
		}
	}


	@Override
	public Page<Encadreur> findByType(String type,int page,int size) {
		return encadreurDao.findByType(type,PageRequest.of(page, size));
	}

	@Override
	public Page<Encadreur> findByQualite(String qualite,int page,int size) {
		return encadreurDao.findByQualite(qualite,PageRequest.of(page, size));
	}

	@Override
	public Encadreur findByUserId(Long id) {
		return encadreurDao.findByUserId(id);
	}

	@Override
	public Encadreur findById(Long id) {
		return encadreurDao.findById(id).get();
	}

	@Override
	public int update(Encadreur encadreur) {
		Encadreur foundedencadreur = findByReference(encadreur.getReference());
		if(foundedencadreur == null) {
			return -1;
		}else if(FieldsUtil.encadreurFields(encadreur)<0) {
			return -2;
		}else {
			if(this.userService.update(encadreur.getUser())<0) {
				return -3;
			}
			encadreurDao.save(encadreur);
			return 1;
		}
	}
	@Transactional
	@Override
	public int removeByReference(String reference) {
		Encadreur encadreur = findByReference(reference);
		if(encadreur == null) {
			return -1;
		}else {
			User u = encadreur.getUser();
			encadreurDao.delete(encadreur);
			userService.removeById(u.getId());
			return 1;
		}
	}

	@Override
	public Encadreur findByReference(String reference) {
		return encadreurDao.findByReference(reference);
	}

	@Override
	public Page<Encadreur> findAllWithPaginition(int page, int size) {
		return encadreurDao.findAll(PageRequest.of(page, size));
	}


	@Override
	public Page<Encadreur> findByUserNomContainsOrUserPrenomContains(String nom, String prenom, int page,
			int size) {
		return encadreurDao.findByUserNomContainsOrUserPrenomContains(nom, prenom, PageRequest.of(page, size));
	}

	@Override
	public ResponseEntity<List<Encadreur>> searchForEncadreurs(Specification<Encadreur> spec) {
		return new ResponseEntity<>(encadreurDao.findAll(Specification.where(spec)), HttpStatus.OK);
	}

	@Override
	public Page<Encadreur> findByCoordinateur(Long id, int page, int size,String sort) {
		if(sort.equals("asc")) {
			return encadreurDao.findByCoordinateur(id, PageRequest.of(page, size,Sort.by(Sort.Direction.ASC,"id")));
		} if(sort.equals("desc")) {
			return encadreurDao.findByCoordinateur(id, PageRequest.of(page, size,Sort.by(Sort.Direction.DESC,"id")));
		}else {
			return null;
		}
	}

	@Override
	public Encadreur findByUserEmail(String email) {
		return encadreurDao.findByUserUsername(email);
	}

	@Override
	public int countEncadreurs() {
		return (int) encadreurDao.count();
	}

	@Override
	public List<Encadreur> findByFiliere(Long id) {
		return encadreurDao.findByFiliere(id);
	}
	
}
