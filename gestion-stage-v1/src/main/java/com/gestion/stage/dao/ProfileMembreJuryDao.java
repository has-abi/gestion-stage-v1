package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.ProfileMembreJury;

@Repository
public interface ProfileMembreJuryDao  extends JpaRepository<ProfileMembreJury, Long>{
	List<ProfileMembreJury> findByEtablissement(Etablissement etablissement);
	List<ProfileMembreJury> findByProfession(String profession);
	
	

}
