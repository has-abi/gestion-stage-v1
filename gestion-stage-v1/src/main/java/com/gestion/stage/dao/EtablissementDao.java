package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Etablissement;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface EtablissementDao extends JpaRepository<Etablissement, Long> {
	Etablissement findByLibelle(String libelle);
}
