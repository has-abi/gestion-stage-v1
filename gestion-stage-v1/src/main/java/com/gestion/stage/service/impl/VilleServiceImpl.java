package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Pays;
import com.gestion.stage.bean.Ville;
import com.gestion.stage.dao.VilleDao;
import com.gestion.stage.service.facade.PaysService;
import com.gestion.stage.service.facade.VilleService;

@Service
public class VilleServiceImpl implements VilleService {
	@Autowired
	private VilleDao villeDao;
	@Autowired
	private PaysService paysService;
	


	@Override
	public Ville findbyId(Long id) {
		return villeDao.findById(id).get();
	}

	@Override
	public List<Ville> findByPaysNom(String nom) {
		return villeDao.findByPaysNom(nom);
	}

	@Override
	public int save(Ville ville) {
		Pays p = paysService.findByNom(ville.getPays().getNom());
		Ville v = villeDao.findByPaysNomAndNom(p.getNom(), ville.getNom());
		if (p == null || v != null) {
			return -1;
		} else if (ville.getNom() == null || ville.getNom() == "" || ville.getCodePostal() == 0) {
			return -2;
		} else {
			ville.setPays(p);
			villeDao.save(ville);
			return 1;
		}

	}

	@Transactional
	@Override
	public int removeByid(Long id) {
		if(id == null || id == 0) {
			return -1;
		}else {
			Ville v = findbyId(id);
			if (v == null) {
				return -1;
			} else {
				villeDao.delete(v);
				return 1;
			}
		}
		
	}

	@Override
	public int update(Ville ville) {
		
		
			Ville v = findbyId(ville.getId());
			if(v==null) {
				return -2;
			}
				villeDao.save(ville);
		return 1;
	}

	@Override
	public List<Ville> findAll() {
		return villeDao.findAll();
	}

	@Override
	public Ville findByPaysNomAndNom(String pays, String nom) {
		return villeDao.findByPaysNomAndNom(pays, nom);
	}

}
