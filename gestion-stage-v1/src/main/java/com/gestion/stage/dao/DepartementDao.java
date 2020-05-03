package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestion.stage.bean.Departement;

@Repository
public interface DepartementDao  extends JpaRepository<Departement, Long>{
	List<Departement> findByEtablissementLibelle(String libelle);
	
}
