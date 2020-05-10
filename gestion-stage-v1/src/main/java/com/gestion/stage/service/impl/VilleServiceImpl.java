package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.OrganismeAccueil;
import com.gestion.stage.bean.Pays;
import com.gestion.stage.bean.Ville;
import com.gestion.stage.dao.VilleDao;
import com.gestion.stage.service.EtablissementService;
import com.gestion.stage.service.OrganismeAccueilService;
import com.gestion.stage.service.PaysService;
import com.gestion.stage.service.VilleService;

@Service
public class VilleServiceImpl implements VilleService {
	@Autowired
	private VilleDao villeDao;
	@Autowired
	private PaysService paysService;
	@Autowired
	private EtablissementService etablissementService;
	@Autowired
	private OrganismeAccueilService organismeAccueilService;

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
				List<Etablissement> etabs = v.getEtablissements();
				etabs.forEach(etab -> etablissementService.removeByLibelle(etab.getLibelle()));
				List<OrganismeAccueil> orgs = v.getOrganismeAccueils();
				orgs.forEach(o->organismeAccueilService.removeById(o.getId()));
				villeDao.delete(v);
				return 1;
			}
		}
		
	}

	@Override
	public int update(Ville ville) {
		if (ville.getId() != null && ville.getId() != 0) {
			Ville v = findbyId(ville.getId());
			if (v == null || ville.getPays() == null) {
				return -1;
			} else if (paysService.findByNom(ville.getPays().getNom()) == null) {
				return -2;
			} else { 
				villeDao.save(ville);
				return 1;
			}
		} else {
			return -3;
		}

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
