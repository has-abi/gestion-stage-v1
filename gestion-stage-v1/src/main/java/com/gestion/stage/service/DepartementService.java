package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Departement;

public interface DepartementService {
	List<Departement> findByEtablissementLibelle(String libelle);
	int save(Departement departement);
	int removeById(Long id);
	int update(Departement departement);
	List<Departement> findAll();
}
