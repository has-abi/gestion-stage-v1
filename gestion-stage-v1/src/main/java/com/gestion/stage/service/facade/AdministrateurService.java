package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.Administrateur;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface AdministrateurService {

	List<Administrateur> findAll();

	int save(Administrateur administrateur);

	int update(Administrateur administrateur);

	int removeByRef(String ref);

	Administrateur findByUserEmail(String email);

	Administrateur findByUserId(Long id);

	Administrateur findByRef(String ref);
}
