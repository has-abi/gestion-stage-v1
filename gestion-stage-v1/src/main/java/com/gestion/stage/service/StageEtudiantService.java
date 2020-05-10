package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.StageEtudiant;

public interface StageEtudiantService {
	List<StageEtudiant> findByStageReference(String reference);
	List<StageEtudiant> findByEtudiantCin(String cin);
	List<StageEtudiant> findAll();
	int save(StageEtudiant stageEtudiant);
	int update(StageEtudiant stageEtudiant);
	int removeById(Long id);
	StageEtudiant findByStageReferenceAndEtudiantCin(String refernce,String cin);
}
