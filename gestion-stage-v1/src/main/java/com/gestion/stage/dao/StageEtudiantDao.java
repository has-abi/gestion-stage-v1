package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.StageEtudiant;

@Repository
public interface StageEtudiantDao extends JpaRepository<StageEtudiant, Long> {
	StageEtudiant findByStageId(Long id);
	StageEtudiant findByEtudiantCin(String cin);
}
