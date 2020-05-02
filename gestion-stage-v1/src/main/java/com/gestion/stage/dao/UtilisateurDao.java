package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Utilisateur;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long>{
	List<Utilisateur> findByAdress(String adress);
	List<Utilisateur> findByDateNaissance(Date dateNaissance);
	List<Utilisateur> findByEmail(String email);
	

}
