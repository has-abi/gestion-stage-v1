package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEncadrant;

public interface StageEncadrantService {
	List<StageEncadrant> findByStage(Stage stage);
	List<StageEncadrant> findByRemarque(String remarque);
	int save(Stage stage);
List<StageEncadrant> findAll();
}
