package com.gestion.stage.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Pays;

@Repository
public interface PaysDao extends JpaRepository<Pays,Long> {
	Pays findByNom(String nom);
}
