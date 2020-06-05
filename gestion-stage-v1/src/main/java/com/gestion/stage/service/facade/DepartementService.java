package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.Departement;

public interface DepartementService {
	List<Departement> findByEtablissementLibelle(String libelle);
	Departement findById(Long id);
	int save(Departement departement);
	int removeById(Long id);
	int update(Departement departement);
	List<Departement> findAll();
	int countDepartements();
}
