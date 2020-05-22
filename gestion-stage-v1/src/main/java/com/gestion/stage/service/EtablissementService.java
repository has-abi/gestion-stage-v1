package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Etablissement;

public interface EtablissementService {
	
	Etablissement findByLibelle(String libelle);
	int save(Etablissement etablissement);
	int removeByLibelle(String libelle);
	int update(Etablissement etablissement);
	List<Etablissement> findAll();
}
