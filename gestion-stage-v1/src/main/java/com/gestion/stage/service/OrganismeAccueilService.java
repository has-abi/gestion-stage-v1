package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.OrganismeAccueil;

public interface OrganismeAccueilService {
	List<OrganismeAccueil> findByTypeOrganismeType(String type);
	List<OrganismeAccueil> findByTypeServiceOrganismeType(String type);
	List<OrganismeAccueil> findByVilleNom(String nom);
	OrganismeAccueil findByRaisonSocial(String raisonSocial);
	List<OrganismeAccueil> findAll();
	int save(OrganismeAccueil organismeAccueil);
	int update(OrganismeAccueil organismeAccueil);
	int removeById(long id);
	
	
	
}
