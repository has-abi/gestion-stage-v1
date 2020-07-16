package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.OrganismeAccueil;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface OrganismeAccueilDao
		extends JpaRepository<OrganismeAccueil, Long>, JpaSpecificationExecutor<OrganismeAccueil> {
	Page<OrganismeAccueil> findByTypeOrganismeType(String type, Pageable pageable);

	Page<OrganismeAccueil> findByTypeServiceOrganismeType(String type, Pageable pageable);

	Page<OrganismeAccueil> findByVilleNom(String nom, Pageable pageable);

	OrganismeAccueil findByRaisonSociale(String raisonSociale);

	@Query(value = "select organisme_accueil.* from stage,coordinateur,organisme_accueil where coordinateur.filiere = :id and stage.coordinateur = coordinateur.id and organisme_accueil.id = stage.organisme_accueil", nativeQuery = true)
	List<OrganismeAccueil> structuresParFiliere(@Param("id") Long id);

	@Query(value = "select DISTINCT organisme_accueil.* from organisme_accueil,stage,coordinateur where coordinateur.filiere = :id and stage.coordinateur = coordinateur.id and  organisme_accueil.id = stage.organisme_accueil", nativeQuery = true)
	List<OrganismeAccueil> findByFiliere(@Param("id") Long id);
}
