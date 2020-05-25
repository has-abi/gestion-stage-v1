package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.StageMembreJury;

@Repository
public interface StageMembreJuryDao extends JpaRepository<StageMembreJury, Long> {
	List<StageMembreJury> findByStageReference(String reference);
	List<StageMembreJury> findByMembreJuryReference(String reference);
	StageMembreJury findByMembreJuryReferenceAndStageReference(String reference,String stage);
	List<StageMembreJury> findByMembreJuryUserNomContainsOrMembreJuryUserPrenomContains(String nom,String prenom);
}
