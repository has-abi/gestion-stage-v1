package com.gestion.stage.service.facade;

import java.util.Date;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.User;
import com.gestion.stage.utils.LoginUser;
import com.gestion.stage.utils.ResponseMessage;

public interface UserService {
	List<User> findByDateNaissanceGreaterThan(Date dateNaissance);

	User findByUsername(String username);

	List<User> findByNomContains(String nom);

	List<User> findByPrenomContains(String prenom);

	List<User> findByDateJoin(Date dateJoin);

	int login(User user);

	int save(User user);

	int register(User user);

	int update(User user);

	int removeById(Long id);

	int newUser(LoginUser user);

	List<User> findAll();

	User findByReference(String reference);

	ResponseEntity<ResponseMessage> uploadProfilePic(String ref, MultipartFile file);

	ResponseEntity<Resource> loadImage(String filename);

	int countusers();

	Page<User> findAllWithPagination(int page, int size, String sort);

	ResponseEntity<List<User>> searchForUsers(Specification<User> spec);

	int confirmUser(String code, String cne);
}
