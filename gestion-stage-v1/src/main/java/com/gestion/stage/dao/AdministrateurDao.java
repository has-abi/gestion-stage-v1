package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.Administrateur;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface AdministrateurDao extends JpaRepository<Administrateur, Long> {
	List<Administrateur> findByProfessionContains(String profession);

	List<Administrateur> findByEtablissement(Etablissement etablissement);

	Administrateur findByUserUsername(String username);

	Administrateur findByUserId(Long id);

	Administrateur findByRef(String ref);
}
