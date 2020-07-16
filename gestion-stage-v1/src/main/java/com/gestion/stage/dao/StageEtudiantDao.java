package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.StageEtudiant;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface StageEtudiantDao extends JpaRepository<StageEtudiant, Long> {
	List<StageEtudiant> findByStageReference(String reference);

	List<StageEtudiant> findByEtudiantCin(String cin);

	List<StageEtudiant> findByEtudiantUserNomContainsOrEtudiantUserPrenomContains(String nom, String prenom);

	@Query(value = "select * from stage_etudiant,stage,etudiant where etudiant.cin = :cin and stage.reference=:reference and stage_etudiant.etudiant = etudiant.id and stage_etudiant.stage=stage.id ", nativeQuery = true)
	StageEtudiant findByStageReferenceAndEtudiantCin(@Param("reference") String refernce, @Param("cin") String cin);
}
