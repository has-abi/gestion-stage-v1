package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.ProfileAdministrateur;

@Repository

public interface ProfilAdmonistrateurDao extends JpaRepository<ProfileAdministrateur, Long>{
	List<ProfileAdministrateur> findByProfession(String profession);
	List<ProfileAdministrateur> findByEtablissement(Etablissement etablissement);
	
	

}
