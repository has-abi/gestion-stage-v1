package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.StageEncadreur;

public interface StageEncadrantService {
	List<StageEncadreur> findByStageId(Long id);
	List<StageEncadreur> findByEncadreurReference(String reference);
	StageEncadreur findByStageIdAndEncadreurReference(Long id,String reference);
	int save(StageEncadreur stageEncadreur);
	List<StageEncadreur> findAll();
	int update(StageEncadreur stageEncadreur);
	int removeById(Long id);
}
