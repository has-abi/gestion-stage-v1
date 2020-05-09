package com.gestion.stage.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.StageEncadreur;

@Repository
public interface StageEncadrantDao extends JpaRepository<StageEncadreur, Long> {
	List<StageEncadreur> findByStageId(Long id);
	List<StageEncadreur> findByEncadreurReference(String reference);
	StageEncadreur findByStageIdAndEncadreurReference(Long id,String reference);
	
}
