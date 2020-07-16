package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.TypeServiceOrganisme;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface TypeServiceOrganismeService {
	TypeServiceOrganisme findByType(String type);

	List<TypeServiceOrganisme> findAll();

	int save(TypeServiceOrganisme typeServiceOrganisme);

	int update(TypeServiceOrganisme typeServiceOrganisme);

	int removeByType(String type);
}
