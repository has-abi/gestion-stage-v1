package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.bean.Departement;
import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.dao.FiliereDao;
import com.gestion.stage.service.CoordinateurService;
import com.gestion.stage.service.DepartementService;
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
	@Autowired
	private DepartementService departementService;

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
		if(filiere.getDepartement() ==  null || filiere.getLibelle() == null || filiere.getLibelle() == "") {
			return -1;
		}else {
			Departement dep = departementService.findById(filiere.getId());
			if(dep == null || dep.getLibelle() == null || dep.getLibelle() == "") {
				return -2;
			}
			List<Filiere> filiers = findByDepartementId(dep.getId());
			for(Filiere fil : filiers) {
				if(fil.getLibelle().equals(filiere.getLibelle())) return -3;
			}
			filiereDao.save(filiere);
			return 1;
		}
		
	}
//***************
	@Override
	public int update(Filiere filiere) {
		if(filiere.getId() != null && filiere.getId() != 0 ) {
			Filiere fil = findById(filiere.getId());
			if(fil == null) {
				return -1;
			}else {
				Departement dep = departementService.findById(filiere.getId());
				if(dep == null || dep.getLibelle() == null || dep.getLibelle() == "") {
					return -2;
				}
				filiere.setDepartement(dep);
				filiereDao.save(filiere);
				return 1;
			}
		}else {
			return -2;
		}
		
	}
	@Transactional
	@Override
	public int removeById(Long id) {
		Filiere filiere = findById(id);
		if(filiere == null) {
			return -1;
		}else {
			Coordinateur coord = filiere.getCoordinateur();
			List<Etudiant> etuds = filiere.getEtudiants();
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
