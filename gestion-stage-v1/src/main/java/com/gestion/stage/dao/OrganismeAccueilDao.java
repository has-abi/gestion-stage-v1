package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.OrganismeAccueil;

@Repository
public interface OrganismeAccueilDao extends JpaRepository<OrganismeAccueil, Long>{
	List<OrganismeAccueil> findByTypeOrganismeType(String type);
	List<OrganismeAccueil> findByTypeServiceOrganismeType(String type);
	List<OrganismeAccueil> findByVilleNom(String nom);
	OrganismeAccueil findByRaisonSociale(String raisonSociale);
}
