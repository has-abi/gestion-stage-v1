package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Departement;
import com.gestion.stage.dao.DepartementDao;
import com.gestion.stage.service.facade.DepartementService;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class DepartementServiceImpl implements DepartementService {

	@Autowired
	private DepartementDao departementDao;

	@Override
	public int save(Departement departement) {
		if (departement.getLibelle() == null || departement.getLibelle() == "") {
			return -1;
		} else {
			List<Departement> deps = findAll();
			for (Departement dep : deps) {
				if (dep.getLibelle().equals(departement.getLibelle())) {
					return -2;
				}
			}
			departementDao.save(departement);
			return 1;
		}
	}

	@Transactional
	@Override
	public int removeById(Long id) {
		Departement dep = departementDao.findById(id).get();
		if (dep == null) {
			return -1;
		} else {

			departementDao.delete(dep);
			return 1;
		}
	}

	@Override
	public int update(Departement departement) {
		if (departement.getId() != null && departement.getId() != 0) {
			Departement dep = departementDao.findById(departement.getId()).get();
			if (dep == null) {
				return -1;
			} else {
				if (departement.getLibelle() == null || departement.getLibelle() == "") {
					return -2;
				}
				departementDao.save(departement);
				return 1;
			}
		} else {
			return -3;
		}
	}

	@Override
	public List<Departement> findAll() {
		return departementDao.findAll();
	}

	@Override
	public Departement findById(Long id) {
		return departementDao.findById(id).get();
	}

	@Override
	public int countDepartements() {
		return (int) departementDao.count();
	}

}
