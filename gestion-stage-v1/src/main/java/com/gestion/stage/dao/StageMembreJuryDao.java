package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.StageMembreJury;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface StageMembreJuryDao extends JpaRepository<StageMembreJury, Long> {
	List<StageMembreJury> findByStageReference(String reference);

	List<StageMembreJury> findByMembreJuryReference(String reference);

	@Query(value = "select * from stage_membre_jury,stage,membre_jury where membre_jury.reference = :reference and stage.reference= :stage and stage_membre_jury.membre_jury= membre_jury.id and stage_membre_jury.stage=stage.id ", nativeQuery = true)
	StageMembreJury findByMembreJuryReferenceAndStageReference(@Param("reference") String reference,
			@Param("stage") String stage);

	List<StageMembreJury> findByMembreJuryUserNomContainsOrMembreJuryUserPrenomContains(String nom, String prenom);
}
