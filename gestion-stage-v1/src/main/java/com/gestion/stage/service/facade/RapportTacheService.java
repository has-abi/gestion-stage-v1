package com.gestion.stage.service.facade;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.Document;
import com.gestion.stage.bean.RapportTache;

public interface RapportTacheService {

	List<RapportTache> findByDateDepot(Date dateDepot);
	List<RapportTache> findByDateModification(Date dateModification);
	List<RapportTache> findByDocument(Document document);
	List<RapportTache> findAll();
	int save(RapportTache rapportTache);

}
