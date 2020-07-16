package com.gestion.stage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.bean.Role;
import com.gestion.stage.bean.User;
import com.gestion.stage.dao.EtudiantDao;
import com.gestion.stage.dao.UserDao;
import com.gestion.stage.service.facade.EtudiantService;
import com.gestion.stage.service.facade.FiliereService;
import com.gestion.stage.service.facade.RoleService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FieldsUtil;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class EtudiantServiceImpl implements EtudiantService {
	@Autowired
	private EtudiantDao etudiantDao;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FiliereService filiereService;
	@Autowired
	private RoleService roleService;

	@Override
	public Etudiant findByCin(String cin) {
		return etudiantDao.findByCin(cin);
	}

	@Override
	public Etudiant findByCodeAppoge(String codeAppoge) {
		return etudiantDao.findByCodeAppoge(codeAppoge);
	}

	@Override
	public List<Etudiant> findByFiliere(Filiere filiere) {
		return etudiantDao.findByFiliere(filiere);
	}

	@Override
	public int save(Etudiant etudiant) {
		if (FieldsUtil.etudiantFields(etudiant) < 0) {
			return -1;
		} else {
			Etudiant foundedEtudByCin = findByCin(etudiant.getCin());
			Etudiant foundeEtudByCode = findByCodeAppoge(etudiant.getCodeAppoge());
			Filiere filiere = filiereService.findById(etudiant.getFiliere().getId());
			if (foundedEtudByCin != null) {
				return -2;
			} else if (foundeEtudByCode != null) {
				return -3;
			} else if (filiere == null) {
				return -4;
			} else {
				etudiant.getUser().setReference("u" + DateUtil.getDate().getTime());
				List<Role> roles = new ArrayList<Role>();
				etudiant.getUser().setRoles(roles);
				etudiant.getUser().getRoles().add(roleService.getEtudiantRole());
				etudiant.getUser().setActive(false);
				if (userService.save(etudiant.getUser()) < 0) {
					return -5;
				}

				etudiant.setUser(userService.findByReference(etudiant.getUser().getReference()));
				etudiantDao.save(etudiant);
				return 1;
			}
		}
	}

	@Override
	public Page<Etudiant> findAll(int page, int size, String sort) {
		if (sort.equals("asc")) {
			return etudiantDao.findAll(PageRequest.of(page, size, Sort.by(Direction.ASC, "id")));
		} else if (sort.equals("desc")) {
			return etudiantDao.findAll(PageRequest.of(page, size, Sort.by(Direction.DESC, "id")));
		} else {
			return null;
		}

	}

	@Override
	public int Update(Etudiant etudiant) {
		Etudiant etud = etudiantDao.findById(etudiant.getId()).get();
		if (etud == null) {
			return -1;
		} else if (FieldsUtil.etudiantFields(etudiant) < 0) {
			return -2;
		} else {
			List<Etudiant> etuds = etudiantDao.findAll();
			for (Etudiant e : etuds) {
				if (e.getId() != etudiant.getId() && (e.getCin().equals(etudiant.getCin())
						|| e.getCodeAppoge().equals(etudiant.getCodeAppoge()))) {
					return -3;
				}
			}
			userDao.save(etudiant.getUser());
			etudiantDao.save(etudiant);
			return 1;
		}
	}

	@Transactional
	@Override
	public int removeByCin(String cin) {
		Etudiant etud = findByCin(cin);
		if (etud == null) {
			return -1;
		} else {
			User u = etud.getUser();
			etudiantDao.delete(etud);
			userService.removeById(u.getId());
			return 1;
		}
	}

	@Override
	public Etudiant findByUserEmail(String email) {
		return etudiantDao.findByUserUsername(email);
	}

	@Override
	public Etudiant findByUserId(Long id) {
		return etudiantDao.findByUserId(id);
	}

	@Override
	public Page<Etudiant> findAllWithPaginition(int page, int size) {
		return etudiantDao.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Etudiant> findByUserNomContainsOrUserPrenomContains(String nom, String prenom, int page, int size) {
		return etudiantDao.findByUserNomContainsOrUserPrenomContains(nom, prenom, PageRequest.of(page, size));
	}

	@Override
	public Page<Etudiant> findByNiveau(String niveau, int page, int size) {
		return etudiantDao.findByNiveau(niveau, PageRequest.of(page, size));
	}

	@Override
	public ResponseEntity<List<Etudiant>> searchForEtudiants(Specification<Etudiant> spec) {
		return new ResponseEntity<>(etudiantDao.findAll(Specification.where(spec)), HttpStatus.OK);
	}

	@Override
	public Page<Etudiant> findByCoordinateur(long id, int page, int size, String sort) {
		if (sort.equals("asc")) {
			return etudiantDao.findByCoordinateur(id, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
		} else if (sort.equals("desc")) {
			return etudiantDao.findByCoordinateur(id, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
		} else if (sort.equals("nom")) {
			return etudiantDao.findByCoordinateur(id, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "nom")));
		} else {
			return null;
		}

	}

	@Override
	public List<Etudiant> findByEncadreurid(Long id) {
		return etudiantDao.findByEncadreurid(id);
	}

	@Override
	public List<Etudiant> findByJuryId(Long id) {
		return etudiantDao.findByJuryId(id);
	}

	@Override
	public int countEtdudiants() {
		return (int) etudiantDao.count();
	}

	@Override
	public List<Etudiant> findByFiliere(Long id) {
		return etudiantDao.findByFiliere(id);
	}

	@Override
	public int validateEtudiant(String cne, String codeAppoge) {
		Etudiant foundedEtudiant = findByCin(cne);
		if (foundedEtudiant == null) {
			return -2;
		} else if (!codeAppoge.equals(foundedEtudiant.getCodeAppoge())) {
			return -1;
		} else {
			return 1;
		}
	}
}
