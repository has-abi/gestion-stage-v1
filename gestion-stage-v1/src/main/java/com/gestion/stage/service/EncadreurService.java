package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Encadreur;
import com.gestion.stage.bean.Utilisateur;

public interface EncadreurService {
	List<Encadreur> findByProfession(String profession);
	List<Encadreur> findByType(String type);
	List<Encadreur> findByQualite(String qualite);
	Encadreur findByUtilisateur(Utilisateur utilisateur);
	Encadreur findById(Long id);
	List<Encadreur> findAll();
	int save(Encadreur encadreur);
	int update(Encadreur encadreur);
	int removeById(Long id);
	Encadreur findByReference(String reference);

}
