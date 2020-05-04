package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.dao.FiliereDao;
import com.gestion.stage.service.CoordinateurService;
import com.gestion.stage.service.EtudiantService;
import com.gestion.stage.service.FiliereService;
@Service
public class FiliereServiceImpl implements FiliereService{
	
	@Autowired
	private FiliereDao filiereDao;
	@Autowired
	private CoordinateurService coordinateurService;
	@Autowired
	private EtudiantService etudiantService;

	@Override
	public List<Filiere> findByDepartementId(Long id) {
		return filiereDao.findByDepartementId(id);
	}

	@Override
	public Filiere findById(Long id) {
		return filiereDao.findById(id).get();
	}

	@Override
	public List<Filiere> findAll() {
		return filiereDao.findAll();
	}

	@Override
	public int save(Filiere filiere) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Filiere filiere) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeById(Long id) {
		Filiere filiere = findById(id);
		if(filiere == null) {
			return -1;
		}else {
			Coordinateur coord = coordinateurService.findByFiliere(filiere);
			List<Etudiant> etuds = etudiantService.findByFiliere(filiere);
			coordinateurService.removeByReference(coord.getReference());
			etuds.forEach(etud->etudiantService.removeByCin(etud.getCin()));
			filiereDao.delete(filiere);
			return 1;
		}
	}

	@Override
	public Filiere findByCoodinateurReference(String reference) {
		return filiereDao.findByCoordinateurReference(reference);
	}

}
