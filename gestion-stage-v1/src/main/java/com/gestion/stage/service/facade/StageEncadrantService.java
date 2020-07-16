package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.StageEncadreur;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface StageEncadrantService {
	List<StageEncadreur> findByStageReference(String reference);

	List<StageEncadreur> findByEncadreurReference(String reference);

	StageEncadreur findByStageReferenceAndEncadreurReference(String stage, String reference);

	int save(StageEncadreur stageEncadreur);

	List<StageEncadreur> findAll();

	int update(StageEncadreur stageEncadreur);

	int removeById(Long id);

	List<StageEncadreur> findByEncadreurUserNomContainsOrEncadreurUserPrenomContains(String nom, String prenom);
}
