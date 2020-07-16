package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface EtudiantDao extends JpaRepository<Etudiant, Long>, JpaSpecificationExecutor<Etudiant> {
	Etudiant findByCin(String cin);

	Etudiant findByCodeAppoge(String codeAppoge);

	List<Etudiant> findByFiliere(Filiere filiere);

	Etudiant findByUserUsername(String username);

	Etudiant findByUserId(Long id);

	Page<Etudiant> findByUserNomContainsOrUserPrenomContains(String nom, String prenom, Pageable pageable);

	Page<Etudiant> findByNiveau(String niveau, Pageable pageable);

	@Query(value = "Select etudiant.* from etudiant,stage_etudiant,stage,coordinateur where coordinateur.user = :id and stage.coordinateur = coordinateur.id and stage_etudiant.stage = stage.id and etudiant.id = stage_etudiant.etudiant ", nativeQuery = true)
	Page<Etudiant> findByCoordinateur(@Param("id") Long id, Pageable pageable);

	@Query(value = "SELECT etudiant.* FROM etudiant,stage,stage_encadreur,stage_etudiant,encadreur where encadreur.user = :id and stage_encadreur.encadreur = encadreur.id and stage.id = stage_encadreur.stage and stage_etudiant.stage = stage.id and etudiant.id = stage_etudiant.etudiant ", nativeQuery = true)
	List<Etudiant> findByEncadreurid(@Param("id") Long id);

	@Query(value = "SELECT etudiant.* FROM etudiant,stage,stage_Membre_jury,stage_etudiant,membre_jury where membre_jury.user = :id and stage_Membre_jury.Membre_jury = membre_jury.id and stage.id = stage_Membre_jury.stage and stage_etudiant.stage = stage.id and etudiant.id = stage_etudiant.etudiant ", nativeQuery = true)
	List<Etudiant> findByJuryId(@Param("id") Long id);

	@Query(value = "select distinct etudiant.* from etudiant,stage,stage_etudiant,coordinateur where coordinateur.filiere = :id and  stage.coordinateur = coordinateur.id and stage_etudiant.stage = stage.id and etudiant.id = stage_etudiant.etudiant", nativeQuery = true)
	List<Etudiant> findByFiliere(@Param("id") Long id);
}
