package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.TypeStage;

public interface StageService {
	List<Stage> findByDateDebut(Date dateDebut);
	List<Stage> findByDateFin(Date dateFin);
	List<Stage> findBySujet(String sujet);
	List<Stage> findByTypeStage(TypeStage typeStage);
List<Stage> findAll();
int save(Stage stage);
}
