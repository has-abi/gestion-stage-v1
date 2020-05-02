package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEncadrant;

@Repository
public interface StageEncadrantDao extends JpaRepository<StageEncadrant, Long> {
	List<StageEncadrant> findByStage(Stage stage);
	List<StageEncadrant> findByRemarque(String remarque);
	
	

}
