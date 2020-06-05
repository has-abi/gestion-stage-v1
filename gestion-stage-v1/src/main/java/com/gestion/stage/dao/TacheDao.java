package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Tache;

@Repository
public interface TacheDao extends JpaRepository<Tache, Long>{
	List<Tache> findByDateCreation(Date dateCreation);
	List<Tache> findByDateLimite(Date dateLimite);
	Tache findByReference(String reference);
	int deleteByReference(String reference);
	List<Tache> findByStageReference(String reference);
	
	
}
