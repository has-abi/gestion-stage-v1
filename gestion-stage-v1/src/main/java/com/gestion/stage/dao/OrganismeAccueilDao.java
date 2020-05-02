package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.OrganismeAccueil;
import com.gestion.stage.bean.TypeOrganisme;

@Repository
public interface OrganismeAccueilDao extends JpaRepository<OrganismeAccueil, Long>{
	List<OrganismeAccueil> findByAdress(String adress);
	List<OrganismeAccueil> findByEmail(String email);
	List<OrganismeAccueil> findByTypeOrganisme(TypeOrganisme typeOrganisme);
	
	
	

}
