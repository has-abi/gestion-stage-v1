package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.TypeStage;

@Repository
public interface TypeStageDao extends JpaRepository<TypeStage, Long>{
	List<TypeStage> findByLibelle(String libelle);
	

}
