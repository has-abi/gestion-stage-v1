package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.StageEtudiant;
import com.gestion.stage.dao.StageEtudiantDao;
import com.gestion.stage.service.StageEtudiantService;
import com.gestion.stage.utils.FieldsUtil;

@Service
public class StageEtudiantServiceImpl implements StageEtudiantService{
	@Autowired
	private StageEtudiantDao stageEtudiantDao;

	@Override
	public List<StageEtudiant> findByStageReference(String reference) {
		return stageEtudiantDao.findByStageReference(reference);
	}

	@Override
	public List<StageEtudiant> findByEtudiantCin(String cin) {
		return stageEtudiantDao.findByEtudiantCin(cin);
	}

	@Override
	public List<StageEtudiant> findAll() {
		return stageEtudiantDao.findAll();
	}

	@Override
	public int save(StageEtudiant stageEtudiant) {
		if(findByStageReferenceAndEtudiantCin(stageEtudiant.getStage().getReference(), stageEtudiant.getEtudiant().getCin()) != null) {
			return 2;
		}else {
			stageEtudiantDao.save(stageEtudiant);
			return 1;
		}
	}

	@Override
	public int update(StageEtudiant stageEtudiant) {
		if(findByStageReferenceAndEtudiantCin(stageEtudiant.getStage().getReference(), stageEtudiant.getEtudiant().getCin()) == null) {
			return -1;
		}else if(FieldsUtil.StageEtudiantFields(stageEtudiant)<0) {
			return -2;
		}else {
				stageEtudiantDao.save(stageEtudiant);
				return 1;
		}
	}
	
	@Override
	public int removeById(Long id) {
		if(id == null || id == 0) {
			return -1;
		}else {
			stageEtudiantDao.deleteById(id);
			return 1;
		}
	}

	@Override
	public StageEtudiant findByStageReferenceAndEtudiantCin(String refernce, String cin) {
		return stageEtudiantDao.findByStageReferenceAndEtudiantCin(refernce, cin);
	}

	@Override
	public List<StageEtudiant> findByEtudiantUtilisateurNomContainsOrEtudiantUtilisateurPrenomContains(String nom,
			String prenom) {
		return stageEtudiantDao.findByEtudiantUtilisateurNomContainsOrEtudiantUtilisateurPrenomContains(nom, prenom);
	}

	

}
