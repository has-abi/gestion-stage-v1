package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.Tache;

public interface TacheService {
	List<Tache> findByContenu(String contenu);
	List<Tache> findByDateCreation(Date dateCreation);
	List<Tache> findByDateLimite(Date dateLimite);
	int save(Tache tache);
	int updateTache(Tache tache);
	List<Tache> findAll();
	
	

}
