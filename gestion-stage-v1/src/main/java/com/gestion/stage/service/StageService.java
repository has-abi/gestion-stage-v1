package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.TypeStage;

public interface StageService {
	List<Stage> findByDateDebut(Date dateDebut);
	List<Stage> findByDateFin(Date dateFin);
	List<Stage> findByTypeStage(TypeStage typeStage);
	List<Stage> findByDateFinBetween(Date date1, Date date2);
	List<Stage> findBySujetContains(String sujet);
	List<Stage> findByOrganismeAccueilRaisonSocial(String raisonSocial);
	Stage findByid(Long id);
	int save(Stage stage);
}
