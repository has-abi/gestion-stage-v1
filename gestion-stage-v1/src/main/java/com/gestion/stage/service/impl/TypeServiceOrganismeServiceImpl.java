package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.TypeServiceOrganisme;
import com.gestion.stage.dao.TypeServiceOrganismeDao;
import com.gestion.stage.service.TypeServiceOrganismeService;
@Service
public class TypeServiceOrganismeServiceImpl implements TypeServiceOrganismeService{
	@Autowired
	private TypeServiceOrganismeDao typeServiceOrganismeDao;
	@Override
	public TypeServiceOrganisme findByType(String type) {
		return typeServiceOrganismeDao.findByType(type);
	}

	@Override
	public List<TypeServiceOrganisme> findAll() {
		return typeServiceOrganismeDao.findAll();
	}

	@Override
	public int save(TypeServiceOrganisme typeServiceOrganisme) {
		TypeServiceOrganisme tso = findByType(typeServiceOrganisme.getType());
		if(tso!=null) {
			return -1;
		}else if(typeServiceOrganisme.getType() == null || typeServiceOrganisme.getType() == "") {
			return -2;
		}else {
			typeServiceOrganismeDao.save(typeServiceOrganisme);
			return 1;
		}
	}

	@Override
	public int update(TypeServiceOrganisme typeServiceOrganisme) {
		TypeServiceOrganisme tso = findByType(typeServiceOrganisme.getType());
		if(tso == null) {
			return -1;
		}else if(typeServiceOrganisme.getType() == null || typeServiceOrganisme.getType() == ""){
			return -2;
		}else{
			List<TypeServiceOrganisme> tsos = findAll();
			for(TypeServiceOrganisme t : tsos) {
				if(t.getId() != typeServiceOrganisme.getId() && t.getType().equals(typeServiceOrganisme.getType())) {
					return -3;
				}
			}
			typeServiceOrganismeDao.save(typeServiceOrganisme);
			return 1;
		}
	}

	@Override
	public int removeByType(String type) {
		TypeServiceOrganisme tso = findByType(type);
		if(tso == null) {
			return -1;
		}else {
			typeServiceOrganismeDao.delete(tso);
			return 1;
		}
	}

}
