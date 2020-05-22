package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Encadreur;
import com.gestion.stage.bean.Utilisateur;
import com.gestion.stage.dao.EncadreurDao;
import com.gestion.stage.service.EncadreurService;
import com.gestion.stage.service.UtilisateurService;
import com.gestion.stage.utils.FieldsUtil;
@Service
public class EncadreurServiceImpl implements EncadreurService{
	
	@Autowired
	private EncadreurDao encadreurDao;
	@Autowired
	private UtilisateurService utilisateurService;
	@Override
	public Page<Encadreur> findByProfession(String profession,int page,int size) {
		return encadreurDao.findByProfession(profession,PageRequest.of(page, size));
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
			if(utilisateurService.register(encadreur.getUtilisateur())<0) {
				return -3;
			}
			encadreur.setUtilisateur(utilisateurService.findByEmail(encadreur.getUtilisateur().getEmail()));
			encadreurDao.save(encadreur);
			return 1;
		}
	}


	@Override
	public Page<Encadreur> findByType(String type,int page,int size) {
		return encadreurDao.findByType(type,PageRequest.of(page, size));
	}

	@Override
	public Page<Encadreur> findByQualite(String qualite,int page,int size) {
		return encadreurDao.findByQualite(qualite,PageRequest.of(page, size));
	}

	@Override
	public Encadreur findByUtilisateurId(Long id) {
		return encadreurDao.findByUtilisateurId(id);
	}

	@Override
	public Encadreur findById(Long id) {
		return encadreurDao.findById(id).get();
	}

	@Override
	public int update(Encadreur encadreur) {
		Encadreur foundedencadreur = findByReference(encadreur.getReference());
		if(foundedencadreur == null) {
			return -1;
		}else if(FieldsUtil.encadreurFields(encadreur)<0) {
			return -2;
		}else {
			encadreur.setUtilisateur(utilisateurService.findByEmail(encadreur.getUtilisateur().getEmail()));
			encadreurDao.save(encadreur);
			return 1;
		}
	}
	@Transactional
	@Override
	public int removeByReference(String reference) {
		Encadreur encadreur = findByReference(reference);
		if(encadreur == null) {
			return -1;
		}else {
			Utilisateur u = encadreur.getUtilisateur();
			encadreurDao.delete(encadreur);
			utilisateurService.removeById(u.getId());
			return 1;
		}
	}

	@Override
	public Encadreur findByReference(String reference) {
		return encadreurDao.findByReference(reference);
	}

	@Override
	public Page<Encadreur> findAllWithPaginition(int page, int size) {
		return encadreurDao.findAll(PageRequest.of(page, size));
	}


	@Override
	public Page<Encadreur> findByUtilisateurNomContainsOrUtilisateurPrenomContains(String nom, String prenom, int page,
			int size) {
		return encadreurDao.findByUtilisateurNomContainsOrUtilisateurPrenomContains(nom, prenom, PageRequest.of(page, size));
	}

	@Override
	public ResponseEntity<List<Encadreur>> searchForEncadreurs(Specification<Encadreur> spec) {
		return new ResponseEntity<>(encadreurDao.findAll(Specification.where(spec)), HttpStatus.OK);
	}
	
}
