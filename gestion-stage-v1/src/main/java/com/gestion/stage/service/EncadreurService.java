package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Encadreur;

public interface EncadreurService {
	List<Encadreur> findByProfession(String profession);
	List<Encadreur> findAll();
	int save(Encadreur encadreur);

}
