package com.gestion.stage.service.facade;

import java.util.Date;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.User;
import com.gestion.stage.utils.ResponseMessage;

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
	ResponseEntity<ResponseMessage> uploadProfilePic(String ref , MultipartFile file);
	ResponseEntity<Resource> loadImage(String filename);
}
