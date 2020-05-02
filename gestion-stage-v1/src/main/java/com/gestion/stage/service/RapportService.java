package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.Rapport;

public interface RapportService {
	List<Rapport> findByDateDepot(Date dateDepot);

	List<Rapport> findByDateSoutenance(Date dateSoutenance);

	List<Rapport> findByDescreption(String descreption);
	int save(Rapport rapport);
	int updateRapport(Rapport rapport);
	List<Rapport> findAll();

}
