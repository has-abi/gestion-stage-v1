package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.MembreJury;

public interface MembreJuryService {
	List<MembreJury> findByEtablissement(Etablissement etablissement);
	List<MembreJury> findByProfession(String profession);
	List<MembreJury> findAll();
	int save(MembreJury membreJury);

}
