package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.StageEncadreur;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface StageEncadrantDao extends JpaRepository<StageEncadreur, Long> {
	List<StageEncadreur> findByStageReference(String reference);

	List<StageEncadreur> findByEncadreurReference(String reference);

	List<StageEncadreur> findByEncadreurUserNomContainsOrEncadreurUserPrenomContains(String nom, String prenom);

	@Query(value = "select * from stage_encadreur,stage,encadreur where encadreur.reference = :reference and stage.reference= :stage and stage_encadreur.encadreur = encadreur.id and stage_encadreur.stage=stage.id ", nativeQuery = true)
	StageEncadreur findByStageReferenceAndEncadreurReference(@Param("stage") String stage,
			@Param("reference") String reference);
}
