package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> ,JpaSpecificationExecutor<User>{
	List<User> findByDateNaissanceGreaterThan(Date dateNaissance);
	User findByUsername(String username);
	User findByReference(String reference);
	List<User> findByNomContains(String nom);
	List<User> findByPrenomContains(String prenom);
	List<User> findByDateJoin(Date dateJoin);
	
	@Query(value="SELECT count(*) as users from user",nativeQuery = true)
	int countUsers();
	
}
