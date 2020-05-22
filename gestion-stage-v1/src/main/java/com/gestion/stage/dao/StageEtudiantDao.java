package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.StageEtudiant;

@Repository
public interface StageEtudiantDao extends JpaRepository<StageEtudiant, Long> {
	List<StageEtudiant> findByStageReference(String reference);
	List<StageEtudiant> findByEtudiantCin(String cin);
	List<StageEtudiant> findByEtudiantUtilisateurNomContainsOrEtudiantUtilisateurPrenomContains(String nom,String prenom);
	StageEtudiant findByStageReferenceAndEtudiantCin(String refernce,String cin);
}
