package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Departement;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.dao.FiliereDao;
import com.gestion.stage.service.facade.DepartementService;
import com.gestion.stage.service.facade.FiliereService;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class FiliereServiceImpl implements FiliereService {

	@Autowired
	private FiliereDao filiereDao;

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
		if (filiere.getDepartement() == null || filiere.getLibelle() == null || filiere.getLibelle() == "") {
			return -1;
		} else {
			filiereDao.save(filiere);
			return 1;
		}

	}

//***************
	@Override
	public int update(Filiere filiere) {
		if (filiere.getId() != null && filiere.getId() != 0) {
			Filiere fil = findById(filiere.getId());
			if (fil == null) {
				return -1;
			} else {
				Departement dep = departementService.findById(filiere.getId());
				if (dep == null || dep.getLibelle() == null || dep.getLibelle() == "") {
					return -2;
				}
				filiere.setDepartement(dep);
				filiereDao.save(filiere);
				return 1;
			}
		} else {
			return -2;
		}

	}

	@Transactional
	@Override
	public int removeById(Long id) {
		Filiere filiere = findById(id);
		if (filiere == null) {
			return -1;
		} else {

			filiereDao.delete(filiere);
			return 1;
		}
	}

	@Override
	public int countFilieres() {
		return (int) filiereDao.count();
	}

}
