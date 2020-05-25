package com.gestion.stage.service.facade;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.User;

public interface UserService {
	List<User> findByDateNaissanceGreaterThan(Date dateNaissance);
	User findByEmail(String email);
	List<User> findByNomContains(String nom);
	List<User> findByPrenomContains(String prenom);
	List<User> findByDateJoin(Date dateJoin);
	int login(User user);
	int save(User user);
	int register(User user);
	int update(User user);
	int removeById(Long id);
	List<User> findAll();
	User findByReference(String reference);
}
