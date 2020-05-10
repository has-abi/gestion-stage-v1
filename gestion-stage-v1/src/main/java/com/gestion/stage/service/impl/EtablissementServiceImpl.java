package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Departement;
import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.Ville;
import com.gestion.stage.dao.EtablissementDao;
import com.gestion.stage.service.DepartementService;
import com.gestion.stage.service.EtablissementService;
import com.gestion.stage.service.VilleService;
@Service
public class EtablissementServiceImpl implements EtablissementService{
	@Autowired
	private EtablissementDao etablissementDao;
	@Autowired
	private VilleService villeService;
	@Autowired
	private DepartementService departementService;

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
		Ville v = villeService.findByPaysNomAndNom(etablissement.getVille().getPays().getNom(),etablissement.getVille().getNom());
		if(v == null) {
			return -1;
		}else if(etablissement.getLibelle() == null || etablissement.getLibelle() == "") {
			return -2;
		}
		else {
			Etablissement etab = findByLibelle(etablissement.getLibelle());
			if(etab!=null) {
				return -3;
			}else {
				etablissement.setVille(v);
				etablissementDao.save(etablissement);
				return 1;
			}
		}
	}
	@Transactional
	@Override
	public int removeByLibelle(String libelle) {
		Etablissement etab = findByLibelle(libelle);
		if(etab == null) {
			return -1;
		}else {
			List<Departement> deps = etab.getDepartements();
			deps.forEach(dep->departementService.removeById(dep.getId()));
			etablissementDao.delete(etab);
			return 1;
		}
	}

	@Override
	public int update(Etablissement etablissement) {
		if(etablissement.getId() != null || etablissement.getId() != 0) {
			Etablissement etab = etablissementDao.findById(etablissement.getId()).get();
			Etablissement etabBylibelle = findByLibelle(etablissement.getLibelle());
			if(etab == null || etablissement.getLibelle() == null || etablissement.getLibelle() == "") {
				return -1;
			}else if(etabBylibelle!=null && etabBylibelle.getId() != etab.getId()) {
				return -2;
			}else {
				etablissementDao.save(etablissement);
				return 1;
			}
		}else {
			return -3;
		}
		
	}

	@Override
	public List<Etablissement> findAll() {
		return etablissementDao.findAll();
	}

}
