package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.User;
import com.gestion.stage.dao.UserDao;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FieldsUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findByDateNaissanceGreaterThan(Date dateNaissance) {
		return userDao.findByDateNaissanceGreaterThan(dateNaissance);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
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
		User foundedutilisateur = findByEmail(user.getEmail());
		if (foundedutilisateur == null) {
			return -1;
		} else if (!foundedutilisateur.getMotPass().equals(user.getMotPass())) {
			return -2;
		} else {
			return 1;
		}
	}

	@Override
	public int register(User user) {
		User foundedUtilisateur = findByEmail(user.getEmail());
		if (foundedUtilisateur != null) {
			return -1;
		} else if (user.getMotPass() == "" || user.getMotPass() == null) {
			return -2;
		} else if (user.getNom() == "" || user.getNom() == null || user.getPrenom() == ""
				|| user.getPrenom() == null) {
			return -3;
		} else {
			user.setDateJoin(DateUtil.getDate());
			userDao.save(user);
			return 1;
		}
	}

	@Override
	public int update(User user) {
		User foundedUtilisateur = userDao.findById(user.getId()).get();
		if (foundedUtilisateur == null) {
			return -1;
		} else if (FieldsUtil.utilisateurFields(user) < 0) {
			return -2;
		} else {
			List<User> users = findAll();
			for (User u : users) {
				if (u.getId() != user.getId() && u.getEmail().equals(user.getEmail())) {
					return -3;
				}
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
		if(u != null) {
			return -1;
		}else if (user.getNom() == "" || user.getNom() == null || user.getPrenom() == ""
				|| user.getPrenom() == null) {
			return -2;
		}else{
			userDao.save(user);	
			return 1;
		}
	}

	@Override
	public User findByReference(String reference) {
		return userDao.findByReference(reference);
	}
	

}
