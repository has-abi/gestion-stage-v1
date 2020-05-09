package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Utilisateur;
import com.gestion.stage.dao.UtilisateurDao;

import com.gestion.stage.service.UtilisateurService;
import com.gestion.stage.util.DateUtil;
import com.gestion.stage.util.FieldsUtil;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	private UtilisateurDao utilisateurDao;

	@Override
	public List<Utilisateur> findByDateNaissanceGreaterThan(Date dateNaissance) {
		return utilisateurDao.findByDateNaissanceGreaterThan(dateNaissance);
	}

	@Override
	public Utilisateur findByEmail(String email) {
		return utilisateurDao.findByEmail(email);
	}

	@Override
	public List<Utilisateur> findByNomContains(String nom) {
		return utilisateurDao.findByNomContains(nom);
	}

	@Override
	public List<Utilisateur> findByPrenomContains(String prenom) {
		return utilisateurDao.findByPrenomContains(prenom);
	}

	@Override
	public List<Utilisateur> findByDateJoin(Date dateJoin) {
		return findByDateJoin(dateJoin);
	}

	@Override
	public int login(Utilisateur utilisateur) {
		Utilisateur foundedutilisateur = findByEmail(utilisateur.getEmail());
		if (foundedutilisateur == null) {
			return -1;
		} else if (!utilisateur.getMotPass().equals(utilisateur.getMotPass())) {
			return -2;
		} else {
			return 1;
		}
	}

	@Override
	public int register(Utilisateur utilisateur) {
		Utilisateur foundedUtilisateur = findByEmail(utilisateur.getEmail());
		if (foundedUtilisateur != null) {
			return -1;
		} else if (utilisateur.getMotPass() == "" || utilisateur.getMotPass() == null) {
			return -2;
		} else if (utilisateur.getNom() == "" || utilisateur.getNom() == null || utilisateur.getPrenom() == ""
				|| utilisateur.getPrenom() == null) {
			return -3;
		} else {
			utilisateur.setDateJoin(DateUtil.getDate());
			utilisateurDao.save(utilisateur);
			return 1;
		}
	}

	@Override
	public int update(Utilisateur utilisateur) {
		Utilisateur foundedUtilisateur = utilisateurDao.findById(utilisateur.getId()).get();
		if (foundedUtilisateur == null) {
			return -1;
		} else if (FieldsUtil.utilisateurFields(utilisateur) < 0) {
			return -2;
		} else {
			List<Utilisateur> utilisateurs = findAll();
			for (Utilisateur u : utilisateurs) {
				if (u.getId() != utilisateur.getId() && u.getEmail().equals(utilisateur.getEmail())) {
					return -3;
				}
			}
			utilisateurDao.save(utilisateur);
			return 1;
		}
	}
	@Transactional
	@Override
	public int removeById(Long id) {
		Utilisateur utilisateur = utilisateurDao.findById(id).get();
		if (utilisateur == null) {
			return 1;
		} else {
			utilisateurDao.delete(utilisateur);
			return 1;
		}
	}

	@Override
	public List<Utilisateur> findAll() {
		return utilisateurDao.findAll();
	}

}
