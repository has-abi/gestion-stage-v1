package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Administrateur;
import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.dao.AdministrateurDao;
import com.gestion.stage.service.AdministrateurService;

@Service
public class AdministrateurServiceImpl implements AdministrateurService{
	@Autowired
	private AdministrateurDao administrateurDao;
	@Override
	public List<Administrateur> findByProfessionContains(String profession) {
		return administrateurDao.findByProfessionContains(profession);
	}

	@Override
	public List<Administrateur> findByEtablissement(Etablissement etablissement) {
		return administrateurDao.findByEtablissement(etablissement);
	}

	@Override
	public List<Administrateur> findAll() {
		return administrateurDao.findAll();
	}

	@Override
	public int save(Administrateur administrateur) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Administrateur findByUtilisateurEmail(String email) {
		return administrateurDao.findByUtilisateurEmail(email);
	}

	@Override
	public int update(Administrateur administrateur) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transactional
	@Override
	public int removeByRef(String ref) {
		
		return 0;
	}

	@Override
	public Administrateur findByUtilisateurId(Long id) {
		return administrateurDao.findByUtilisateurId(id);
	}

	@Override
	public Administrateur findByRef(String ref) {
		return administrateurDao.findByRef(ref);
	}

}
