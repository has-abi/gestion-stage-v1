package com.gestion.stage.service.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gestion.stage.bean.OrganismeAccueil;

public interface OrganismeAccueilService {
	Page<OrganismeAccueil> findByTypeOrganismeType(String type,int page,int size);
	Page<OrganismeAccueil> findByTypeServiceOrganismeType(String type,int page,int size);
	Page<OrganismeAccueil> findByVilleNom(String nom,int page,int size);
	OrganismeAccueil findByRaisonSocial(String raisonSocial);
	List<OrganismeAccueil> findAll();
	int save(OrganismeAccueil organismeAccueil);
	int update(OrganismeAccueil organismeAccueil);
	int removeById(long id);
	Page<OrganismeAccueil> findAllWithPagination(int page, int size);
	ResponseEntity<List<OrganismeAccueil>> searchForOrganismes(Specification<OrganismeAccueil> spec);
	
}
