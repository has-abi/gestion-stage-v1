package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Encadreur;

public interface EncadreurService {
	List<Encadreur> findByProfession(String profession);
	List<Encadreur> findByType(String type);
	List<Encadreur> findByQualite(String qualite);
	Encadreur findByUtilisateurId(Long id);
	Encadreur findById(Long id);
	List<Encadreur> findAll();
	int save(Encadreur encadreur);
	int update(Encadreur encadreur);
	int removeByReference(String reference);
	Encadreur findByReference(String reference);

}
