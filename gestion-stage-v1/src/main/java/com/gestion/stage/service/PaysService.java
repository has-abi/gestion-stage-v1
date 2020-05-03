package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Pays;

public interface PaysService {
	Pays findByNom(String nom);
	int save(Pays paye);
	List<Pays> findAll();
}
