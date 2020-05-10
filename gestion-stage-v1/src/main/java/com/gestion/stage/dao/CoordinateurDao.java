package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Coordinateur;
@Repository
public interface CoordinateurDao extends JpaRepository<Coordinateur, Long>{
	Coordinateur findByReference(String reference);
	Coordinateur findByFiliereId(Long id);
	Coordinateur findByUtilisateurId(Long id);
}
