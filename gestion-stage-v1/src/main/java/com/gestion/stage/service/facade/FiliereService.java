package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.Filiere;

public interface FiliereService {
	List<Filiere> findByDepartementId(Long id);
	Filiere findById(Long id);
	List<Filiere> findAll();
	int save(Filiere filiere);
	int update(Filiere filiere);
	int removeById(Long id);
	int countFilieres();

}
