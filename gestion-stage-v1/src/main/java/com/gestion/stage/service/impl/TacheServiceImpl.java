package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Encadreur;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.Tache;
import com.gestion.stage.dao.TacheDao;
import com.gestion.stage.service.facade.EncadreurService;
import com.gestion.stage.service.facade.StageService;
import com.gestion.stage.service.facade.TacheService;
import com.gestion.stage.utils.DateUtil;

@Service
public class TacheServiceImpl implements TacheService {
	@Autowired
	private TacheDao tacheDao;
	@Autowired
	private StageService stageService;
	@Autowired
	private EncadreurService encadreurService;

	@Override
	public List<Tache> findByDateCreation(Date dateCreation) {
		return tacheDao.findByDateCreation(dateCreation);
	}

	@Override
	public List<Tache> findByDateLimite(Date dateLimite) {
		return tacheDao.findByDateLimite(dateLimite);
	}

	@Override
	public Tache findByReference(String reference) {
		return tacheDao.findByReference(reference);
	}

	@Override
	public int save(Tache tache) {
		Stage stage = stageService.findByReference(tache.getStage().getReference());
		Encadreur e = encadreurService.findByReference(tache.getEncadreur().getReference());
		if (tache.getContenu() == "" || tache.getContenu() == null || tache.getDateLimite() == null) {
			return -1;
		} else if(stage == null || e == null) {
			return -2;
		}else {
			tache.setStage(stage);
			tache.setEncadreur(e);
			tache.setDateCreation(DateUtil.getDate());
			tacheDao.save(tache);
			return 1;
		}

	}

	@Override
	public int updateTache(Tache tache) {
		Tache foundTche = findByReference(tache.getReference());
		Stage stage = stageService.findByReference(tache.getStage().getReference());
		Encadreur e = encadreurService.findByReference(tache.getEncadreur().getReference());
		if (foundTche == null || stage == null || e == null) {
			return -1;
		} else {
			tacheDao.save(tache);
			return 1;

		}

	}

	@Override
	public List<Tache> findAll() {
		return tacheDao.findAll();
	}

	@Override
	public int validerTache(String reference) {
		Tache foundedTache = findByReference(reference);
		if (foundedTache == null) {
			return -1;
		} else {
			foundedTache.setValider(true);
			foundedTache.setDateValidation(DateUtil.getDate());
			tacheDao.save(foundedTache);
			return 1;
		}
	}

	@Override
	public List<Tache> findByStageReference(String reference) {
		return tacheDao.findByStageReference(reference);
	}

	@Override
	@Transactional
	public int deleteByReference(String reference) {
		Tache tacheFounded = findByReference(reference);
		if (tacheFounded == null) {
			return -1;
		} else {
			tacheDao.delete(tacheFounded);
			return 1;
		}

	}

}
