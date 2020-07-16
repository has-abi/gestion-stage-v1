package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Ville;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface VilleDao extends JpaRepository<Ville, Long> {
	List<Ville> findByPaysNom(String nom);

	Ville findByPaysNomAndNom(String nomPays, String nom);

	@Query(value = "select ville.* from ville,stage,organisme_accueil,coordinateur where coordinateur.filiere = :id and coordinateur.id = stage.coordinateur and stage.organisme_accueil = organisme_accueil.id and organisme_accueil.ville = ville.id", nativeQuery = true)
	List<Ville> villesParFilier(@Param("id") Long id);
}
