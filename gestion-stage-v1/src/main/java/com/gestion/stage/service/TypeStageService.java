package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.TypeStage;

public interface TypeStageService {
	TypeStage findByLibelle(String libelle);
	List<TypeStage> findAll();
	int save(TypeStage typeStage);
	int update(TypeStage typeStage);
	int removeBylibelle(String libelle);
}
