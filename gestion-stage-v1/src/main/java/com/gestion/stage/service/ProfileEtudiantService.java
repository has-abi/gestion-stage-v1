package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.ProfileEtudiant;
import com.gestion.stage.bean.Specialite;

public interface ProfileEtudiantService {
	List<ProfileEtudiant> findByCin(String cin);
	List<ProfileEtudiant> findByCodeAppoge(String codeAppoge);
	List<ProfileEtudiant> findBySpecialite(Specialite specialite);
	int save(ProfileEtudiant profileEtudiant);
	List<ProfileEtudiant> findALL();
	

}
