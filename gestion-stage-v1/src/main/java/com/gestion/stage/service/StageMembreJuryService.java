package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.StageMembreJury;

public interface StageMembreJuryService {
	StageMembreJury findByStage(Long id);
	StageMembreJury findBMembreJuryReference(String reference);
	List<StageMembreJury> findAll();
	int save(StageMembreJury stageMembreJury);
	int update(StageMembreJury stageMembreJury);
	int removeByid(StageMembreJury stageMembreJury);
}
