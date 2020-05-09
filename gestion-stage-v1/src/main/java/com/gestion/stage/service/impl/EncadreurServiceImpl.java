package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Encadreur;
import com.gestion.stage.bean.Utilisateur;
import com.gestion.stage.dao.EncadreurDao;
import com.gestion.stage.service.EncadreurService;
import com.gestion.stage.service.UtilisateurService;
import com.gestion.stage.util.FieldsUtil;
@Service
public class EncadreurServiceImpl implements EncadreurService{
	
	@Autowired
	private EncadreurDao encadreurDao;
	@Autowired
	private UtilisateurService utilisateurService;
	@Override
	public List<Encadreur> findByProfession(String profession) {
		return encadreurDao.findByProfession(profession);
	}

	@Override
	public List<Encadreur> findAll() {
		return encadreurDao.findAll();
	}

	@Override
	public int save(Encadreur encadreur) {
		Encadreur foundedencadreur = findByReference(encadreur.getReference());
		if(foundedencadreur != null) {
			return -1;
		}else if(FieldsUtil.encadreurFields(encadreur)<0) {
			return -2;
		}else {
			encadreur.getUtilisateur().setRole(2);
			utilisateurService.register(encadreur.getUtilisateur());
			encadreur.setUtilisateur(utilisateurService.findByEmail(encadreur.getUtilisateur().getEmail()));
			encadreurDao.save(encadreur);
			return 1;
		}
	}


	@Override
	public List<Encadreur> findByType(String type) {
		return encadreurDao.findByType(type);
	}

	@Override
	public List<Encadreur> findByQualite(String qualite) {
		return encadreurDao.findByQualite(qualite);
	}

	@Override
	public Encadreur findByUtilisateur(Utilisateur utilisateur) {
		return encadreurDao.findByUtilisateur(utilisateur);
	}

	@Override
	public Encadreur findById(Long id) {
		return encadreurDao.findById(id).get();
	}

	@Override
	public int update(Encadreur encadreur) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transactional
	@Override
	public int removeById(Long id) {
		
		return 0;
	}

	@Override
	public Encadreur findByReference(String reference) {
		return encadreurDao.findByReference(reference);
	}

}
