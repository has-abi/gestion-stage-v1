package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.TypeOrganisme;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface TypeOrganismeService {
	TypeOrganisme findByType(String type);

	List<TypeOrganisme> findAll();

	int save(TypeOrganisme typeOrganisme);

	int update(TypeOrganisme typeOrganisme);

	int removeByType(String type);
}
