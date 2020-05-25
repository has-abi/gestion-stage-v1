package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.StageEncadreur;
import com.gestion.stage.dao.StageEncadrantDao;
import com.gestion.stage.service.facade.StageEncadrantService;
import com.gestion.stage.utils.FieldsUtil;

@Service
public class StageEncadreurServiceImpl implements StageEncadrantService{
	@Autowired
	private StageEncadrantDao stageEncadrantDao;
	
	@Override
	public int save(StageEncadreur stageEncadreur) {
			StageEncadreur se = findByStageReferenceAndEncadreurReference(stageEncadreur.getStage().getReference(), stageEncadreur.getEncadreur().getReference());
			if(se != null) {
				return -1;
			}else {
				stageEncadrantDao.save(stageEncadreur);
				return 1;
			}
	}

	@Override
	public List<StageEncadreur> findAll() {
		return stageEncadrantDao.findAll();
	}

	@Override
	public int update(StageEncadreur stageEncadreur) {
		StageEncadreur so = findByStageReferenceAndEncadreurReference(stageEncadreur.getEncadreur().getReference(), stageEncadreur.getStage().getReference());
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
	public List<StageEncadreur> findByStageReference(String reference) {
		return stageEncadrantDao.findByStageReference(reference);
	}

	@Override
	public List<StageEncadreur> findByEncadreurReference(String reference) {
		return stageEncadrantDao.findByEncadreurReference(reference);
	}

	@Override
	public StageEncadreur findByStageReferenceAndEncadreurReference(String stage, String reference) {
		return stageEncadrantDao.findByStageReferenceAndEncadreurReference(stage, reference);
	}

	@Override
	public List<StageEncadreur> findByEncadreurUserNomContainsOrEncadreurUserPrenomContains(String nom,
			String prenom) {
		return stageEncadrantDao.findByEncadreurUserNomContainsOrEncadreurUserPrenomContains(nom, prenom);
	}

}
