package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.MembreJury;

public interface MembreJuryService {
	MembreJury findByUtilisateurId(Long id);
	MembreJury findByUtilisateurEmail(String email);
	List<MembreJury> findByProfession(String profession);
	List<MembreJury> findAll();
	MembreJury findByReference(String reference);
	int save(MembreJury membreJury);
	int update(MembreJury membreJury);
	int removeById(Long id);

}
