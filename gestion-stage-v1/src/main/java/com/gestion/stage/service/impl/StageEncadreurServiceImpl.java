package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Encadreur;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEncadreur;
import com.gestion.stage.dao.StageEncadrantDao;
import com.gestion.stage.service.EncadreurService;
import com.gestion.stage.service.StageEncadrantService;
import com.gestion.stage.service.StageService;
import com.gestion.stage.util.FieldsUtil;

@Service
public class StageEncadreurServiceImpl implements StageEncadrantService{
	@Autowired
	private StageEncadrantDao stageEncadrantDao;
	@Autowired
	private EncadreurService encadreurService;
	@Autowired
	private StageService stageService;
	

	@Override
	public int save(StageEncadreur stageEncadreur) {
		Encadreur encadreur = encadreurService.findByReference(stageEncadreur.getEncadreur().getReference());
		Stage stage = stageService.findByid(stageEncadreur.getStage().getId());
		if(FieldsUtil.StageEncadreurFields(stageEncadreur)<0) {
			return -1;
		}else if(encadreur == null || stage == null) {
			return -2;
		}else {
			StageEncadreur se = findByStageIdAndEncadreurReference(stageEncadreur.getStage().getId(), stageEncadreur.getEncadreur().getReference());
			if(se != null) {
				return 2;
			}else {
				stageEncadreur.setEncadreur(encadreur);
				stageEncadreur.setStage(stage);
				stageEncadrantDao.save(stageEncadreur);
				return 1;
			}
		}
	}

	@Override
	public List<StageEncadreur> findAll() {
		return stageEncadrantDao.findAll();
	}

	@Override
	public int update(StageEncadreur stageEncadreur) {
		StageEncadreur so = stageEncadrantDao.findById(stageEncadreur.getId()).get();
		if(so == null) {
			return -1;
		}else if(FieldsUtil.StageEncadreurFields(stageEncadreur)<0) {
			return -2;
		}else {
			stageEncadrantDao.save(stageEncadreur);
			return 1;
		}
	}

	@Override
	public int removeById(Long id) {
		StageEncadreur so = stageEncadrantDao.findById(id).get();
		if(so == null) {
			return -1;
		}else {
			stageEncadrantDao.delete(so);
			return 1;
		}
	}

	@Override
	public List<StageEncadreur> findByStageId(Long id) {
		return stageEncadrantDao.findByStageId(id);
	}

	@Override
	public List<StageEncadreur> findByEncadreurReference(String reference) {
		return stageEncadrantDao.findByEncadreurReference(reference);
	}

	@Override
	public StageEncadreur findByStageIdAndEncadreurReference(Long id, String reference) {
		return findByStageIdAndEncadreurReference(id, reference);
	}

}
