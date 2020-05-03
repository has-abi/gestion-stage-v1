package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.bean.Specialite;
import com.gestion.stage.bean.Utilisateur;
import com.gestion.stage.dao.CoordinateurDao;
import com.gestion.stage.service.CoordinateurService;
import com.gestion.stage.service.SpecialiteService;
@Service
public class CoordinateurServiceImpl implements CoordinateurService{
	
	@Autowired
	private CoordinateurDao coordinateurDao;
	@Autowired
	private SpecialiteService specialiteService;
	@Override
	public Coordinateur findByReference(String reference) {
		return coordinateurDao.findByReference(reference);
	}

	@Override
	public Coordinateur findBySpecialite(Specialite specialite) {
		return coordinateurDao.findBySpecialite(specialite);
	}

	@Override
	public int save(Coordinateur coordinateur) {
		Coordinateur coord = findByReference(coordinateur.getReference());
		Specialite spec = specialiteService.findByLibelle(coordinateur.getSpecialite().getLibelle());
		if( coord != null) {
			return -1;
		}else if(spec == null) {
			return -2;
		}else {
			coordinateur.setSpecialite(spec);
			coordinateurDao.save(coordinateur);
			return 1;
		}
	}

	@Override
	public int removeByReference(String reference) {
		Coordinateur coord = findByReference(reference);
		if(coord == null) {
			return -1;
		}else {
			coordinateurDao.delete(coord);
			return 1;
		}
	}

	@Override
	public int update(Coordinateur coordinateur) {
		Coordinateur coord = findByReference(coordinateur.getReference());
		if(coord == null) {
			return -1;
		}else {
			coordinateurDao.save(coord);
			return 1;
		}
	}

	@Override
	public List<Coordinateur> findAll() {
		return coordinateurDao.findAll();
	}

	@Override
	public Coordinateur findByUtilisateur(Utilisateur utilisateur) {
		return coordinateurDao.findByUtilisateur(utilisateur);
	}

}
