package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.TypeOrganisme;

public interface TypeOrganismeService {
	TypeOrganisme findByType(String type);
	List<TypeOrganisme> findAll();
	int save(TypeOrganisme typeOrganisme);
	int update(TypeOrganisme typeOrganisme);
	int removeByType(String type);
}
