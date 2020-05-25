package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.Administrateur;

public interface AdministrateurService {
	List<Administrateur> findByProfessionContains(String profession);
	List<Administrateur> findByEtablissement(Etablissement etablissement);
	List<Administrateur> findAll();
	int save(Administrateur administrateur);
	int update(Administrateur administrateur);
	int removeByRef(String ref);
	Administrateur findByUserEmail(String email);
	Administrateur findByUserId(Long id);
	Administrateur findByRef(String ref);
}
