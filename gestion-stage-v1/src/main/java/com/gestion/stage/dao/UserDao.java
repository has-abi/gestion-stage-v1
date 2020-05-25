package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	List<User> findByDateNaissanceGreaterThan(Date dateNaissance);
	User findByEmail(String email);
	User findByReference(String reference);
	List<User> findByNomContains(String nom);
	List<User> findByPrenomContains(String prenom);
	List<User> findByDateJoin(Date dateJoin);
}
