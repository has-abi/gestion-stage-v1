package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEtudiant;

@Repository
public interface StageEtudiantDao extends JpaRepository<StageEtudiant, Long> {
	List<StageEtudiant> findByStage(Stage stage);
	List<StageEtudiant> findByRemarque(String remarque);
	

}
