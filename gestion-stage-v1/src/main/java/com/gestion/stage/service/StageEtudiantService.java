package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.StageEtudiant;

public interface StageEtudiantService {
	StageEtudiant findByStageId(Long id);
	StageEtudiant findByEtudiantCin(String cin);
	List<StageEtudiant> findAll();
	int save(StageEtudiant stageEtudiant);
	int update(StageEtudiant stageEtudiant);
	int removeById(Long id);
}
