package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.TypeServiceOrganisme;

public interface TypeServiceOrganismeService {
	TypeServiceOrganisme findByType(String type);
	List<TypeServiceOrganisme> findAll();
	int save(TypeServiceOrganisme typeServiceOrganisme);
	int update(TypeServiceOrganisme typeServiceOrganisme);
	int removeByType(String type);
}
