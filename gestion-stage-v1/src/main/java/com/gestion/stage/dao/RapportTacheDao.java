package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Document;
import com.gestion.stage.bean.RapportTache;

@Repository
public interface RapportTacheDao extends JpaRepository<RapportTache, Long>{

	List<RapportTache> findByDateDepot(Date dateDepot);
	List<RapportTache> findByDateModification(Date dateModification);
	List<RapportTache> findByDocument(Document document);
	
	
	
}
