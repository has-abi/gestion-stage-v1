package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;

@Repository
public interface EtudiantDao extends JpaRepository<Etudiant, Long>{
	Etudiant findByCin(String cin);
	Etudiant findByCodeAppoge(String codeAppoge);
	List<Etudiant> findByFiliere(Filiere filiere);
	Etudiant findByUtilisateurEmail(String email);
	
}
