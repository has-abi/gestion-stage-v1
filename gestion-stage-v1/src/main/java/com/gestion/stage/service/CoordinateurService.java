package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Coordinateur;

public interface CoordinateurService {
	Coordinateur findByReference(String reference);
	Coordinateur findByFiliereId(Long id);
	int save(Coordinateur coordinateur);
	int removeByReference(String reference);
	int update(Coordinateur coordinateur);
	List<Coordinateur> findAll();
	Coordinateur findByUtilisateurId(Long id);
	
}
