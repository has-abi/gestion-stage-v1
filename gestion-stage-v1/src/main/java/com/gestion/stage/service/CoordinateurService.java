package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.bean.Specialite;
import com.gestion.stage.bean.Utilisateur;

public interface CoordinateurService {
	Coordinateur findByReference(String reference);
	Coordinateur findBySpecialite(Specialite specialite);
	int save(Coordinateur coordinateur);
	int removeByReference(String reference);
	int update(Coordinateur coordinateur);
	List<Coordinateur> findAll();
	Coordinateur findByUtilisateur(Utilisateur utilisateur);
	
}
