package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.ProfileMembreJury;

public interface ProfileMembreJuryService {
	List<ProfileMembreJury> findByEtablissement(Etablissement etablissement);
	List<ProfileMembreJury> findByProfession(String profession);
	List<ProfileMembreJury> findAll();
	int save(ProfileMembreJury profileMembreJury);

}
