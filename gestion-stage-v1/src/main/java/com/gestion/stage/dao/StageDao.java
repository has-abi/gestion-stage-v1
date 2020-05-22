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
public interface StageDao extends JpaRepository<Stage, Long>,JpaSpecificationExecutor<Stage> {
	
	@Query(value="SELECT * FROM stage WHERE date_debut = DATE_FORMAT(:dateDebut,'%Y-%m-%d')"
			,countQuery = "SELECT count(*) FROM stage WHERE date_debut = DATE_FORMAT(:dateDebut,'%Y-%m-%d')"
			,nativeQuery = true)
	Page<Stage> findByDateDebut(String dateDebut,Pageable pageable);
	@Query(value="SELECT * FROM stage WHERE date_fin = DATE_FORMAT(:dateFin,'%Y-%m-%d')"
			,countQuery = "SELECT count(*) FROM stage WHERE date_fin = DATE_FORMAT(:dateFin,'%Y-%m-%d')"
			,nativeQuery = true)
	Page<Stage> findByDateFin(@Param("dateFin") String dateFin,Pageable pageable);
	@Query(value="SELECT * FROM stage WHERE date_fin between  DATE_FORMAT(:date1,'%Y-%m-%d') and DATE_FORMAT(:date2,'%Y-%m-%d')",nativeQuery = true)
	List<Stage> findByDateFinBetween(@Param("date1") String date1,@Param("date2") String date2);
	Page<Stage> findBySujetContains(String sujet,Pageable pageable);
	Page<Stage> findByOrganismeAccueilRaisonSociale(String raisonSocial,Pageable pageable);
	Stage findByReference(String reference);
	
}
