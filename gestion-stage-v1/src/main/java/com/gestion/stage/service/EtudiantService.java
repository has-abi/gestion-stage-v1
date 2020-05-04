package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;

public interface EtudiantService {
	Etudiant findByCin(String cin);
	Etudiant findByCodeAppoge(String codeAppoge);
	List<Etudiant> findByFiliere(Filiere filiere);
	int save(Etudiant etudiant);
	List<Etudiant> findALL();
	int Update(Etudiant etudiant);
	int removeByCin(String cin);
	

}
