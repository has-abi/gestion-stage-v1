package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.Utilisateur;

public interface UtilisateurService {
	List<Utilisateur> findByDateNaissanceGreaterThan(Date dateNaissance);
	Utilisateur findByEmail(String email);
	List<Utilisateur> findByNomContains(String nom);
	List<Utilisateur> findByPrenomContains(String prenom);
	List<Utilisateur> findByDateJoin(Date dateJoin);
	int login(Utilisateur utilisateur);
	int register(Utilisateur utilisateur);
	int update(Utilisateur utilisateur);
	int removeById(Long id);
	List<Utilisateur> findAll();
}
