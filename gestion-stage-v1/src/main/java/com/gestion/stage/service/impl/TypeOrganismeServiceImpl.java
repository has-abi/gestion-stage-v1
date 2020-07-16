package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.TypeOrganisme;
import com.gestion.stage.dao.TypeOrganismeDao;
import com.gestion.stage.service.facade.TypeOrganismeService;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class TypeOrganismeServiceImpl implements TypeOrganismeService {

	@Autowired
	private TypeOrganismeDao typeOrganismeDao;

	@Override
	public TypeOrganisme findByType(String type) {
		return typeOrganismeDao.findByType(type);
	}

	@Override
	public List<TypeOrganisme> findAll() {
		return typeOrganismeDao.findAll();
	}

	@Override
	public int save(TypeOrganisme typeOrganisme) {
		TypeOrganisme to = findByType(typeOrganisme.getType());
		if (to != null) {
			return -1;
		} else if (typeOrganisme.getType() == null || typeOrganisme.getType() == "") {
			return -2;
		} else {
			typeOrganismeDao.save(typeOrganisme);
			return 1;
		}
	}

	@Override
	public int update(TypeOrganisme typeOrganisme) {
		TypeOrganisme to = typeOrganismeDao.findById(typeOrganisme.getId()).get();
		if (to == null) {
			return -1;
		} else if (typeOrganisme.getType() == null || typeOrganisme.getType() == "") {
			return -2;
		} else {
			List<TypeOrganisme> tos = findAll();
			for (TypeOrganisme t : tos) {
				if (t.getId() == to.getId() && to.getType().equals(typeOrganisme.getType())) {
					return -3;
				}
			}
			typeOrganismeDao.save(typeOrganisme);
			return 1;
		}
	}

	// maybe there's some code to add here
	@Override
	public int removeByType(String type) {
		TypeOrganisme to = findByType(type);
		if (to == null) {
			return -1;
		} else {
			typeOrganismeDao.delete(to);
			return 1;
		}
	}

}
