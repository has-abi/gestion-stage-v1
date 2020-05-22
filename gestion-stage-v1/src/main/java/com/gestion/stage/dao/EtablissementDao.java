package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Etablissement;

@Repository
public interface EtablissementDao extends JpaRepository<Etablissement, Long> {
	Etablissement findByLibelle(String libelle);
}
