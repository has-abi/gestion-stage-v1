package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Pays;
import com.gestion.stage.bean.Ville;
import com.gestion.stage.dao.PaysDao;
import com.gestion.stage.service.PaysService;
import com.gestion.stage.service.VilleService;

@Service
public class PaysServiceImpl implements PaysService{
	@Autowired
	private PaysDao paysDao;
	@Autowired
	private VilleService villeService;
	@Override
	public Pays findByNom(String nom) {
		return paysDao.findByNom(nom);
	}

	@Override
	public int save(Pays pays) {
		Pays foundedPays = findByNom(pays.getNom());
		if(foundedPays!=null) {
			return -1;
		}else {
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
		if(foundedPays == null) {
			return -1;
		}else {
			List<Pays> ps = findAll();
			for(Pays p : ps) {
				if(p.getId()!=pays.getId() && p.getNom().equals(pays.getNom())) {
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
		if(pays == null) {
			return -1;
		}else {
			List<Ville> villes = pays.getVilles();
			villes.forEach(ville->villeService.removeByid(ville.getId()));
			paysDao.delete(pays);
			return 1;
		}
	}

}
