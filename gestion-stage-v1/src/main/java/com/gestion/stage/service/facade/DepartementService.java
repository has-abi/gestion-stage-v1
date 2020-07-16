package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.Departement;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface DepartementService {

	Departement findById(Long id);

	int save(Departement departement);

	int removeById(Long id);

	int update(Departement departement);

	List<Departement> findAll();

	int countDepartements();
}
