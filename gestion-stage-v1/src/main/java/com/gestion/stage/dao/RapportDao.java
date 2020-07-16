package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Rapport;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface RapportDao extends JpaRepository<Rapport, Long>, JpaSpecificationExecutor<Rapport> {
	List<Rapport> findByDateDepot(Date dateDepot);

	Rapport findByReference(String reference);

	List<Rapport> findByDescreptionContains(String descreption);

	@Query(value = "SELECT count(*) as rapportC from rapport", nativeQuery = true)
	int countrapports();

}
