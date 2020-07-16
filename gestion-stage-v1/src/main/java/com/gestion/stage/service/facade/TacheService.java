package com.gestion.stage.service.facade;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gestion.stage.bean.Tache;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface TacheService {
	List<Tache> findByDateCreation(Date dateCreation);

	List<Tache> findByDateLimite(Date dateLimite);

	Tache findByReference(String reference);

	int deleteByReference(String reference);

	int save(Tache tache);

	int updateTache(Tache tache);

	List<Tache> findAll();

	int validerTache(String reference);
	
	int effectuerTache(String reference);
	
	List<Tache> findByStageReference(String reference);
	
	Page<Tache> findByEncadreurReference(String reference,int page,int size);
	
	Page<Tache> findByEtudiant(Long id,int page,int size);
	
	ResponseEntity<List<Tache>> searchForTaches(Specification<Tache> spec);
}
