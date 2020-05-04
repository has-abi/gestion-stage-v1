package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.bean.Utilisateur;

public interface CoordinateurService {
	Coordinateur findByReference(String reference);
	Coordinateur findByFiliere(Filiere filiere);
	int save(Coordinateur coordinateur);
	int removeByReference(String reference);
	int update(Coordinateur coordinateur);
	List<Coordinateur> findAll();
	Coordinateur findByUtilisateur(Utilisateur utilisateur);
	
}
