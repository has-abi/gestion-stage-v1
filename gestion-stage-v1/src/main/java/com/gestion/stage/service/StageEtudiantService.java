package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEtudiant;

public interface StageEtudiantService {
	List<StageEtudiant> findByStage(Stage stage);
	List<StageEtudiant> findByRemarque(String remarque);
	
	List<StageEtudiant> findAll();
	int save(Stage stage, List<Etudiant> etudiants);
	


}
