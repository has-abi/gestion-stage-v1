package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEncadreur;

@Repository
public interface StageEncadrantDao extends JpaRepository<StageEncadreur, Long> {
	List<StageEncadreur> findByStage(Stage stage);
	List<StageEncadreur> findByRemarque(String remarque);
	
	

}
