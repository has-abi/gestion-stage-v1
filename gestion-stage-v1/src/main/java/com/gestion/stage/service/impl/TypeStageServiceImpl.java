package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.TypeStage;
import com.gestion.stage.dao.TypeStageDao;
import com.gestion.stage.service.TypeStageService;

@Service
public class TypeStageServiceImpl implements TypeStageService{
	@Autowired
	private TypeStageDao typeStageDao;
	@Override
	public TypeStage findByLibelle(String libelle) {
		return typeStageDao.findByLibelle(libelle);
	}

	@Override
	public List<TypeStage> findAll() {
		return typeStageDao.findAll();
	}
	
	@Override
	public int save(TypeStage typeStage) {
		TypeStage ts = findByLibelle(typeStage.getLibelle());
		if(ts != null) {
			return -1;
		}else if(typeStage.getLibelle() == null || typeStage.getLibelle() == "") {
			return -2;
		}else {
			typeStageDao.save(typeStage);
			return 1;
		}
	}

	@Override
	public int update(TypeStage typeStage) {
		TypeStage ts = typeStageDao.findById(typeStage.getId()).get();
		if(ts == null) {
			return -1;
		}else if(typeStage.getLibelle() == null || typeStage.getLibelle() == "") {
			return -2;
		}else {
			List<TypeStage> tss = findAll();
			for(TypeStage t : tss) {
				if(t.getId()!=ts.getId() && t.getLibelle().equals(typeStage.getLibelle()));
			}
		}
		return 0;
	}
	//we back here soon
	@Override
	public int removeBylibelle(String libelle) {
		TypeStage ts = findByLibelle(libelle);
		if(ts == null) {
			return -1;
		}else {	
			typeStageDao.delete(ts);
			return 1;
		}
	}

}
