package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Utilisateur;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long>{
	List<Utilisateur> findByDateNaissanceGreaterThan(Date dateNaissance);
	Utilisateur findByEmail(String email);
	Utilisateur findByReference(String reference);
	List<Utilisateur> findByNomContains(String nom);
	List<Utilisateur> findByPrenomContains(String prenom);
	List<Utilisateur> findByDateJoin(Date dateJoin);
}
