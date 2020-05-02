package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.Ville;

@Repository
public interface EtabissementDao extends JpaRepository<Etablissement, Long> {
	
List<Etablissement> findByLibelle(String libelle);
List<Etablissement> findByVille(Ville ville);


}
