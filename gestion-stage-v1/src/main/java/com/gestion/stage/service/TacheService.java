package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.Tache;

public interface TacheService {
	List<Tache> findByDateCreation(Date dateCreation);
	List<Tache> findByDateLimite(Date dateLimite);
	Tache findByReference(String reference);
	int deleteByReference(String reference);
	int save(Tache tache);
	int updateTache(Tache tache);
	List<Tache> findAll(); 
         }





















































































