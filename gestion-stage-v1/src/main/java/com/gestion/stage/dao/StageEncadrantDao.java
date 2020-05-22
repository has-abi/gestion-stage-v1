package com.gestion.stage.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.StageEncadreur;

@Repository
public interface StageEncadrantDao extends JpaRepository<StageEncadreur, Long> {
	List<StageEncadreur> findByStageReference(String reference);
	List<StageEncadreur> findByEncadreurReference(String reference);
	List<StageEncadreur> findByEncadreurUtilisateurNomContainsOrEncadreurUtilisateurPrenomContains(String nom,String prenom);
	StageEncadreur findByStageReferenceAndEncadreurReference(String stage,String reference);
	
}
