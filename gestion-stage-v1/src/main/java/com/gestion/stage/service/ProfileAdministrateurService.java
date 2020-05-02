package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.ProfileAdministrateur;

public interface ProfileAdministrateurService {
	List<ProfileAdministrateur> findByProfession(String profession);
	List<ProfileAdministrateur> findByEtablissement(Etablissement etablissement);
	List<ProfileAdministrateur> findAll();
	int save(ProfileAdministrateur profileAdministrateur);

}
