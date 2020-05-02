package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.OrganismeAccueil;
import com.gestion.stage.bean.TypeOrganisme;

public interface OrganismeAccueilService {
	List<OrganismeAccueil> findByAdress(String adress);
	List<OrganismeAccueil> findByEmail(String email);
	List<OrganismeAccueil> findByTypeOrganisme(TypeOrganisme typeOrganisme);
	List<OrganismeAccueil> findAll();
	int save(OrganismeAccueil organismeAccueil);
	
	
	
}
