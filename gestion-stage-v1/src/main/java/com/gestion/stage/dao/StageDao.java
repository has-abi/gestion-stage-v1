package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Stage;

@Repository
public interface StageDao extends JpaRepository<Stage, Long>, JpaSpecificationExecutor<Stage> {

	@Query(value = "SELECT * FROM stage WHERE date_debut = DATE_FORMAT(:dateDebut,'%Y-%m-%d')", countQuery = "SELECT count(*) FROM stage WHERE date_debut = DATE_FORMAT(:dateDebut,'%Y-%m-%d')", nativeQuery = true)
	Page<Stage> findByDateDebut(String dateDebut, Pageable pageable);

	@Query(value = "SELECT * FROM stage WHERE date_fin = DATE_FORMAT(:dateFin,'%Y-%m-%d')", countQuery = "SELECT count(*) FROM stage WHERE date_fin = DATE_FORMAT(:dateFin,'%Y-%m-%d')", nativeQuery = true)
	Page<Stage> findByDateFin(@Param("dateFin") String dateFin, Pageable pageable);

	@Query(value = "SELECT * FROM stage WHERE date_fin between  DATE_FORMAT(:date1,'%Y-%m-%d') and DATE_FORMAT(:date2,'%Y-%m-%d')", nativeQuery = true)
	List<Stage> findByDateFinBetween(@Param("date1") String date1, @Param("date2") String date2);

	Page<Stage> findBySujetContains(String sujet, Pageable pageable);

	Page<Stage> findByOrganismeAccueilRaisonSociale(String raisonSocial, Pageable pageable);

	Stage findByReference(String reference);

	Page<Stage> findByCoordinateurReference(String reference, Pageable pageable);

	Long countByCoordinateurReference(String reference);

	@Query(value = "SELECT count(*) from stage_etudiant where stage_etudiant.etudiant = :id ", nativeQuery = true)
	Long countByEtudiant(@Param("id") Long id);

	@Query(value = "SELECT count(*) from stage_encadreur where stage_encadreur.encadreur = :id ", nativeQuery = true)
	Long countByEncadreur(@Param("id") Long id);

	@Query(value = "SELECT count(*) from stage_membre_jury where stage_membre_jury.membre_jury= :id ", nativeQuery = true)
	Long countByJury(@Param("id") Long id);

	@Query(value = "select stage.* from stage,etudiant,stage_etudiant where etudiant.id = :id and stage_etudiant.etudiant = etudiant.id and stage_etudiant.stage = stage.id", countQuery = "SELECT count(*) from stage_etudiant where stage_etudiant.etudiant = :id", nativeQuery = true)
	Page<Stage> findByEtudiant(Long id, Pageable pageable);

	@Query(value = "select stage.* from stage,encadreur,stage_encadreur where encadreur.id = :id and stage_encadreur.encadreur= encadreur.id and stage_encadreur.stage = stage.id", countQuery = "SELECT count(*) from stage_encadreur where stage_encadreur.encadreur = :id", nativeQuery = true)
	Page<Stage> findByEncadreur(Long id, Pageable pageable);

	@Query(value = "select stage.* from stage,membre_jury,stage_membre_jury where membre_jury.id = :id and stage_membre_jury.membre_jury = membre_jury.id and stage_membre_jury.stage = stage.id", countQuery = "SELECT count(*) from stage_membre_jury where stage_membre_jury.membre_jury = :id", nativeQuery = true)
	Page<Stage> findByJury(Long id, Pageable pageable);
	Stage findByRapportReference(String reference);

}
