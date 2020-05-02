package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Departement;
import com.gestion.stage.bean.Specialite;

public interface SpecialiteService {
	List<Specialite> findByLibelle(String libelle);
	List<Specialite> findByDepartement(Departement departement);
	List<Specialite> findAll();
	int save(Specialite specialite);

}
