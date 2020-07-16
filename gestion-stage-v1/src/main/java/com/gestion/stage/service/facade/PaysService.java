package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.Pays;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface PaysService {
	Pays findByNom(String nom);

	int save(Pays pays);

	int update(Pays pays);

	int removeByNom(String nom);

	List<Pays> findAll();
	
	int countPays();
}
