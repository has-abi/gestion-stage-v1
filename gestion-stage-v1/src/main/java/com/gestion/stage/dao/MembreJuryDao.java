package com.gestion.stage.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.MembreJury;

@Repository
public interface MembreJuryDao  extends JpaRepository<MembreJury, Long>{
	
	MembreJury findByUtilisateurId(Long id);
	MembreJury findByUtilisateurEmail(String email);
	List<MembreJury> findByProfession(String profession);
	MembreJury findByReference(String reference);
}
