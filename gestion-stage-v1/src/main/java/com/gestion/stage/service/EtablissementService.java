package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.Ville;

public interface EtablissementService {
	List<Etablissement> findByLibelle(String libelle);

	List<Etablissement> findByVille(Ville ville);

	int save(Etablissement etablissement);

	List<Etablissement> findAll();
}
