package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.bean.Specialite;
import com.gestion.stage.bean.Utilisateur;
@Repository
public interface CoordinateurDao extends JpaRepository<Coordinateur, Long>{
	Coordinateur findByReference(String reference);
	Coordinateur findBySpecialite(Specialite specialite);
	Coordinateur findByUtilisateur(Utilisateur utilisateur);
}
