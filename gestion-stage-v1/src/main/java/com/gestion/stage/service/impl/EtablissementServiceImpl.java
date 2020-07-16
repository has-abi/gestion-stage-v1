package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.dao.EtablissementDao;
import com.gestion.stage.service.facade.EtablissementService;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class EtablissementServiceImpl implements EtablissementService {
	@Autowired
	private EtablissementDao etablissementDao;

	@Override
	public int save(Etablissement etablissement) {
		if (etablissement.getLibelle() == null || etablissement.getLibelle() == "") {
			return -1;
		} else {

			etablissementDao.save(etablissement);
			return 1;

		}
	}

	@Override
	public int update(Etablissement etablissement) {
		if (etablissement.getLibelle() == null || etablissement.getLibelle() == "") {
			return -1;
		} else {
			etablissementDao.save(etablissement);
			return 1;
		}
	}

	@Override
	public List<Etablissement> findAll() {

		return etablissementDao.findAll();
	}

}
