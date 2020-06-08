package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.OrganismeAccueil;

@Repository
public interface OrganismeAccueilDao
		extends JpaRepository<OrganismeAccueil, Long>, JpaSpecificationExecutor<OrganismeAccueil> {
	Page<OrganismeAccueil> findByTypeOrganismeType(String type, Pageable pageable);

	Page<OrganismeAccueil> findByTypeServiceOrganismeType(String type, Pageable pageable);

	Page<OrganismeAccueil> findByVilleNom(String nom, Pageable pageable);

	OrganismeAccueil findByRaisonSociale(String raisonSociale);

	@Query(value="select organisme_accueil.* from ville,stage,organisme_accueil,coordinateur where coordinateur.filiere = :id and coordinateur.id = stage.coordinateur and stage.organisme_accueil = organisme_accueil.id ",nativeQuery = true)
	List<OrganismeAccueil> structuresParFiliere(Long id);
}
