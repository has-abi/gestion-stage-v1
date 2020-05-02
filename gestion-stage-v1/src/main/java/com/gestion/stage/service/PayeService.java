package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Paye;

public interface PayeService {
	List<Paye> findByNom(String nom);
	int save(Paye paye);
	List<Paye> findAll();
}
