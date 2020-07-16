package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.StageEtudiant;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface StageEtudiantService {
	List<StageEtudiant> findByStageReference(String reference);

	List<StageEtudiant> findByEtudiantCin(String cin);

	List<StageEtudiant> findAll();

	int save(StageEtudiant stageEtudiant);

	int update(StageEtudiant stageEtudiant);

	int removeById(Long id);

	StageEtudiant findByStageReferenceAndEtudiantCin(String refernce, String cin);

	List<StageEtudiant> findByEtudiantUserNomContainsOrEtudiantUserPrenomContains(String nom, String prenom);
}
