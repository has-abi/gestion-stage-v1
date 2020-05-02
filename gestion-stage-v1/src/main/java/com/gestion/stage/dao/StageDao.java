package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.TypeStage;

@Repository
public interface StageDao extends JpaRepository<Stage, Long> {
	List<Stage> findByDateDebut(Date dateDebut);
	List<Stage> findByDateFin(Date dateFin);
	List<Stage> findBySujet(String sujet);
	List<Stage> findByTypeStage(TypeStage typeStage);
	

}
