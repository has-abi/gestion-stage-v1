package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Pays;
import com.gestion.stage.dao.PaysDao;
import com.gestion.stage.service.facade.PaysService;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class PaysServiceImpl implements PaysService {
	@Autowired
	private PaysDao paysDao;

	@Override
	public Pays findByNom(String nom) {
		return paysDao.findByNom(nom);
	}

	@Override
	public int save(Pays pays) {
		Pays foundedPays = findByNom(pays.getNom());
		if (foundedPays != null) {
			return -1;
		} else {
			paysDao.save(pays);
			return 1;
		}

	}

	@Override
	public List<Pays> findAll() {
		return paysDao.findAll();
	}

	@Override
	public int update(Pays pays) {
		Pays foundedPays = paysDao.findById(pays.getId()).get();
		if (foundedPays == null) {
			return -1;
		} else {
			List<Pays> ps = findAll();
			for (Pays p : ps) {
				if (p.getId() != pays.getId() && p.getNom().equals(pays.getNom())) {
					return -2;
				}
			}
			paysDao.save(pays);
			return 1;
		}
	}

	@Transactional
	@Override
	public int removeByNom(String nom) {
		Pays pays = findByNom(nom);
		if (pays == null) {
			return -1;
		} else {
			paysDao.delete(pays);
			return 1;
		}
	}

	@Override
	public int countPays() {
		return (int) paysDao.count();
	}

}
