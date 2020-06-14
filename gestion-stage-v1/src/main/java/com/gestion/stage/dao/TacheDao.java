package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Tache;

@Repository
public interface TacheDao extends JpaRepository<Tache, Long>,JpaSpecificationExecutor<Tache>{
	List<Tache> findByDateCreation(Date dateCreation);
	List<Tache> findByDateLimite(Date dateLimite);
	Tache findByReference(String reference);
	List<Tache> findByStageReference(String reference); 
	Page<Tache> findByEncadreurReference(String reference,Pageable pageable);
	
	@Query(value = "select tache.* from tache,stage,stage_etudiant where stage_etudiant.etudiant=:id and stage.id = stage_etudiant.stage and tache.stage = stage.id",countQuery = "SELECT count(*) from stage_etudiant where stage_etudiant.etudiant = :id",nativeQuery = true)
	Page<Tache> findByEtudiant(@Param("id") Long id,Pageable pageable);
	
	
	
}
