package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.Administrateur;

public interface AdministrateurService {
	List<Administrateur> findByProfession(String profession);
	List<Administrateur> findByEtablissement(Etablissement etablissement);
	List<Administrateur> findAll();
	int save(Administrateur administrateur);

}
