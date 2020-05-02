package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Departement;
import com.gestion.stage.bean.Etablissement;

public interface DepartementService {
	List<Departement> findByLibelle(String libelle);
	List<Departement> findByEtablissement(Etablissement etablissement);
	int save(Departement departement);
	List<Departement> findAll();
}
