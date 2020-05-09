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
	List<Stage> findByTypeStage(TypeStage typeStage);
	List<Stage> findByDateFinBetween(Date date1, Date date2);
	List<Stage> findBySujetContains(String sujet);
	List<Stage> findByOrganismeAccueilRaisonSociale(String raisonSocial);
	Stage findByid(Long id);

}
