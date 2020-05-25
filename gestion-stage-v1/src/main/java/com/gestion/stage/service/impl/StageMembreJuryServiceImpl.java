package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.StageMembreJury;
import com.gestion.stage.dao.StageMembreJuryDao;
import com.gestion.stage.service.facade.StageMembreJuryService;

@Service
public class StageMembreJuryServiceImpl  implements StageMembreJuryService{
	@Autowired
	private StageMembreJuryDao stageMembreJuryDao;


	@Override
	public List<StageMembreJury> findAll() {
		return stageMembreJuryDao.findAll();
	}

	@Override
	public int save(StageMembreJury stageMembreJury) {
		if(findByMembreJuryReferenceAndStageReference(stageMembreJury.getMembreJury().getReference(), stageMembreJury.getStage().getReference()) != null) {
			return 2;
		}else {
			stageMembreJuryDao.save(stageMembreJury);
			return 1;
		}
	}

	@Override
	public int update(StageMembreJury stageMembreJury) {
		if(findByMembreJuryReferenceAndStageReference(stageMembreJury.getMembreJury().getReference(), stageMembreJury.getStage().getReference()) == null) {
			return -1;
		}else {
				stageMembreJuryDao.save(stageMembreJury);
				return 1;
		}
	}

	@Override
	public int removeByid(Long id) {
		if(id!=null && id!=0) {
			StageMembreJury sm =stageMembreJuryDao.findById(id).get();
			stageMembreJuryDao.delete(sm);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public List<StageMembreJury> findByStageReference(String reference) {
		return stageMembreJuryDao.findByStageReference(reference);
	}

	@Override
	public List<StageMembreJury> findBMembreJuryReference(String reference) {
		return stageMembreJuryDao.findByMembreJuryReference(reference);
	}

	@Override
	public StageMembreJury findByMembreJuryReferenceAndStageReference(String reference, String stage) {
		return stageMembreJuryDao.findByMembreJuryReferenceAndStageReference(reference, stage);
	}

	@Override
	public List<StageMembreJury> findByMembreJuryUserNomContainsOrMembreJuryUserPrenomContains(String nom,
			String prenom) {
		return stageMembreJuryDao.findByMembreJuryUserNomContainsOrMembreJuryUserPrenomContains(nom, prenom);
	}

}
