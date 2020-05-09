package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.StageMembreJury;

@Repository
public interface StageMembreJuryDao extends JpaRepository<StageMembreJury, Long> {
	StageMembreJury findByStage(Long id);
	StageMembreJury findByMembreJuryReference(String reference);

}
