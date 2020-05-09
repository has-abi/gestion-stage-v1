package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.OrganismeAccueil;
import com.gestion.stage.bean.TypeOrganisme;
import com.gestion.stage.bean.TypeServiceOrganisme;
import com.gestion.stage.bean.Ville;
import com.gestion.stage.dao.OrganismeAccueilDao;
import com.gestion.stage.service.OrganismeAccueilService;
import com.gestion.stage.service.TypeOrganismeService;
import com.gestion.stage.service.TypeServiceOrganismeService;
import com.gestion.stage.service.VilleService;
import com.gestion.stage.util.FieldsUtil;
@Service
public class OrganismeAccueilServiceImpl implements OrganismeAccueilService{
	@Autowired
	private OrganismeAccueilDao organismeAccueilDao;
	@Autowired
	private TypeServiceOrganismeService typeServiceOrganismeService;
	@Autowired 
	private TypeOrganismeService typeOrganismeService;
	@Autowired
	private VilleService villeService;
	@Override
	public List<OrganismeAccueil> findByTypeOrganismeType(String type) {
		return organismeAccueilDao.findByTypeOrganismeType(type);
	}

	@Override
	public List<OrganismeAccueil> findByTypeServiceOrganismeType(String type) {
		return findByTypeServiceOrganismeType(type);
	}

	@Override
	public List<OrganismeAccueil> findByVilleNom(String nom) {
		return organismeAccueilDao.findByVilleNom(nom);
	}

	@Override
	public OrganismeAccueil findByRaisonSocial(String raisonSocial) {
		return organismeAccueilDao.findByRaisonSociale(raisonSocial);
	}

	@Override
	public List<OrganismeAccueil> findAll() {
		return organismeAccueilDao.findAll();
	}

	@Override
	public int save(OrganismeAccueil organismeAccueil) {
		TypeOrganisme to = typeOrganismeService.findByType(organismeAccueil.getTypeOrganisme().getType());
		TypeServiceOrganisme tso = typeServiceOrganismeService.findByType(organismeAccueil.getTypeServiceOrganisme().getType());
		Ville v = villeService.findByPaysNomAndNom(organismeAccueil.getVille().getPays().getNom(), organismeAccueil.getVille().getNom());
		OrganismeAccueil orgAccueil = findByRaisonSocial(organismeAccueil.getRaisonSociale());
		if(orgAccueil !=null) {
			return -1;
		}
		else if(to == null || tso == null || v == null ) {
			return -2;
		}else if(FieldsUtil.OrganismeFields(organismeAccueil)<0) {
			return -3;
		}else {
			organismeAccueil.setVille(v);
			organismeAccueil.setTypeOrganisme(to);
			organismeAccueil.setTypeServiceOrganisme(tso);
			organismeAccueilDao.save(organismeAccueil);
			return 1;
		}
	}

	@Override
	public int update(OrganismeAccueil organismeAccueil) {
		OrganismeAccueil orgAccueil = organismeAccueilDao.findById(organismeAccueil.getId()).get();
		if(orgAccueil == null) {
			return -1;
		}else if(FieldsUtil.OrganismeFields(organismeAccueil)<0) {
			return -2;
		}else {
			List<OrganismeAccueil> orgAccueils = findAll();
			for(OrganismeAccueil or : orgAccueils) {
				if(or.getId()!=orgAccueil.getId() && or.getRaisonSociale().equals(organismeAccueil.getRaisonSociale())) {
					return -3;
				}
			}
			organismeAccueilDao.save(organismeAccueil);
			return 1;
		}
	}

	@Override
	public int removeById(long id) {
		OrganismeAccueil orgaAccueil = organismeAccueilDao.findById(id).get();
		if(orgaAccueil == null) {
			return -1;
		}else {
			organismeAccueilDao.delete(orgaAccueil);
			return 1;
		}
	}

}
