package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEncadreur;

public interface StageEncadrantService {
	List<StageEncadreur> findByStage(Stage stage);
	List<StageEncadreur> findByRemarque(String remarque);
	int save(Stage stage);
List<StageEncadreur> findAll();
}
