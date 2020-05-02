package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.ProfileEtudiant;
import com.gestion.stage.bean.Specialite;

@Repository
public interface ProfileEtudiantDao extends JpaRepository<ProfileEtudiant, Long>{
	List<ProfileEtudiant> findByCin(String cin);
	List<ProfileEtudiant> findByCodeAppoge(String codeAppoge);
	List<ProfileEtudiant> findBySpecialite(Specialite specialite);
	
	

}
