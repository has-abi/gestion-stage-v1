package com.gestion.stage.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.OrganismeAccueil;

@Repository
public interface OrganismeAccueilDao extends JpaRepository<OrganismeAccueil, Long>,JpaSpecificationExecutor<OrganismeAccueil>{
	Page<OrganismeAccueil> findByTypeOrganismeType(String type,Pageable pageable);
	Page<OrganismeAccueil> findByTypeServiceOrganismeType(String type,Pageable pageable);
	Page<OrganismeAccueil> findByVilleNom(String nom,Pageable pageable);
	OrganismeAccueil findByRaisonSociale(String raisonSociale);
}
