package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Rapport;

@Repository
public interface RapportDao extends JpaRepository<Rapport, Long> {
	List<Rapport> findByDateDepot(Date dateDepot);

	List<Rapport> findByDateSoutenance(Date dateSoutenance);

	List<Rapport> findByDescreption(String descreption);
}
