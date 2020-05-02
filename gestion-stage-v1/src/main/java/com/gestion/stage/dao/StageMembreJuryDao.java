package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageMembreJury;

@Repository
public interface StageMembreJuryDao  extends JpaRepository<StageMembreJury, Long>{
	List<StageMembreJury> findByStage(Stage stage);
	List<StageMembreJury> findByRemarque(String remarque);
	
	
	

}
