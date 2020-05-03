package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.dao.EtablissementDao;
import com.gestion.stage.service.EtablissementService;
import com.gestion.stage.service.VilleService;
@Service
public class EtablissementServiceImpl implements EtablissementService{
	@Autowired
	private EtablissementDao etablissementDao;
	@Autowired
	private VilleService villeService;

	@Override
	public Etablissement findByLibelle(String libelle) {
		return etablissementDao.findByLibelle(libelle);
	}

	@Override
	public List<Etablissement> findByVilleId(Long id) {
		return etablissementDao.findByVilleId(id);
	}

	@Override
	public int save(Etablissement etablissement) {
		return 0;
	}

	@Override
	public int removeByLibelle(String libelle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Etablissement etablissement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Etablissement> findAll() {
		return etablissementDao.findAll();
	}

}
