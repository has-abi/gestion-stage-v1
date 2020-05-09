package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.TypeStage;
import com.gestion.stage.service.StageService;
@Service
public class StageServiceImpl implements StageService{

	@Override
	public List<Stage> findByDateDebut(Date dateDebut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stage> findByDateFin(Date dateFin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stage> findByTypeStage(TypeStage typeStage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stage> findByDateFinBetween(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stage> findBySujetContains(String sujet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stage> findByOrganismeAccueilRaisonSocial(String raisonSocial) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stage findByid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Stage stage) {
		// TODO Auto-generated method stub
		return 0;
	}

}
