package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Departement;
import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.dao.DepartementDao;
import com.gestion.stage.service.facade.DepartementService;
import com.gestion.stage.service.facade.EtablissementService;
import com.gestion.stage.service.facade.FiliereService;

@Service
public class DepartementServiceImpl implements DepartementService {

	@Autowired
	private DepartementDao departementDao;
	@Autowired
	private EtablissementService etablissementService;
	@Autowired
	private FiliereService filiereService;

	@Override
	public List<Departement> findByEtablissementLibelle(String libelle) {
		return departementDao.findByEtablissementLibelle(libelle);
	}

	@Override
	public int save(Departement departement) {
		Etablissement etablissement = etablissementService.findByLibelle(departement.getEtablissement().getLibelle());
		if (etablissement == null || departement.getLibelle() == null || departement.getLibelle() == "") {
			return -1;
		} else {
			List<Departement> deps = findByEtablissementLibelle(departement.getEtablissement().getLibelle());
			for (Departement dep : deps) {
				if (dep.getLibelle().equals(departement.getLibelle())) {
					return -2;
				}
			}
			departement.setEtablissement(etablissement);
			departementDao.save(departement);
			return 1;
		}
	}

	@Transactional
	@Override
	public int removeById(Long id) {
		Departement dep = departementDao.findById(id).get();
		if (dep == null) {
			return -1;
		} else {
			List<Filiere> filieres = dep.getFilieres();
			filieres.forEach(f -> filiereService.removeById(f.getId()));
			departementDao.delete(dep);
			return 1;
		}
	}

	@Override
	public int update(Departement departement) {
		if (departement.getId() != null && departement.getId() != 0) {
			Departement dep = departementDao.findById(departement.getId()).get();
			if (dep == null) {
				return -1;
			} else {
				Etablissement etablissement = etablissementService
						.findByLibelle(departement.getEtablissement().getLibelle());
				if (etablissement == null || departement.getLibelle() == null || departement.getLibelle() == "") {
					return -2;
				}
				departement.setEtablissement(etablissement);
				departementDao.save(departement);
				return 1;
			}
		} else {
			return -3;
		}
	}

	@Override
	public List<Departement> findAll() {
		return departementDao.findAll();
	}

	@Override
	public Departement findById(Long id) {
		return departementDao.findById(id).get();
	}

}
