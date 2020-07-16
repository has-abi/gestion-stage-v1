package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.StageMembreJury;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface StageMembreJuryService {
	List<StageMembreJury> findByStageReference(String reference);

	List<StageMembreJury> findBMembreJuryReference(String reference);

	StageMembreJury findByMembreJuryReferenceAndStageReference(String reference, String stage);

	List<StageMembreJury> findAll();

	int save(StageMembreJury stageMembreJury);

	int update(StageMembreJury stageMembreJury);

	int removeByid(Long id);

	List<StageMembreJury> findByMembreJuryUserNomContainsOrMembreJuryUserPrenomContains(String nom, String prenom);
}
