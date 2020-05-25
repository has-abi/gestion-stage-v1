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

import com.gestion.stage.bean.MembreJury;
import com.gestion.stage.bean.User;
import com.gestion.stage.dao.MembreJuryDao;
import com.gestion.stage.service.facade.MembreJuryService;
import com.gestion.stage.service.facade.RoleService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FieldsUtil;
@Service
public class MembreJuryServiceImpl implements MembreJuryService{
	@Autowired
	private MembreJuryDao membreJuryDao;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Override
	public MembreJury findByUserId(Long id) {
		return membreJuryDao.findByUserId(id);
	}

	@Override
	public Page<MembreJury> findByProfession(String profession,int page,int size) {
		return membreJuryDao.findByProfession(profession,PageRequest.of(page, size));
	}

	@Override
	public List<MembreJury> findAll() {
		return membreJuryDao.findAll();
	}

	@Override
	public int save(MembreJury membreJury) {
		if(FieldsUtil.juryFields(membreJury)<0) {
			return -1;
		}else {
			MembreJury foundedJury = findByReference(membreJury.getReference());
			if(foundedJury != null) {
				return -2;
			}else {
				membreJury.getUser().setReference("u"+DateUtil.getDate().getTime());
				membreJury.getUser().setRole(roleService.getJuryRole());
				if (userService.register(membreJury.getUser())<0) {
					return -3;
				};
				membreJury.setUser(userService.findByReference(membreJury.getUser().getReference()));
				membreJuryDao.save(membreJury);
				return 1;
			}
		}
	}

	@Override
	public int update(MembreJury membreJury) {
		MembreJury foundedjury = membreJuryDao.findById(membreJury.getId()).get();
		if(foundedjury == null) {
			return -1;
		}else if(FieldsUtil.juryFields(membreJury)<0) {
			return -2;
		}else {
			List<MembreJury> memJuries = findAll();
			for(MembreJury m : memJuries) {
				if(m.getId() == membreJury.getId() && m.getReference().equals(membreJury.getReference())) {
					return -3;
				}
			}
			membreJuryDao.save(membreJury);
			return 1;
		}
	}
	@Transactional
	@Override
	public int removeByReference(String reference) {
		MembreJury foundedjury = findByReference(reference);
		if(foundedjury == null) {
			return -1;
		}else {
			User u = foundedjury.getUser();
			membreJuryDao.delete(foundedjury);
			userService.removeById(u.getId());
			return 1;
		}
	}

	@Override
	public MembreJury findByUserEmail(String email) {
		return membreJuryDao.findByUserEmail(email);
	}

	@Override
	public MembreJury findByReference(String reference) {
		return membreJuryDao.findByReference(reference);
	}

	@Override
	public Page<MembreJury> findAllWithPaginition(int page, int size) {
		return membreJuryDao.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<MembreJury> findByUserNomContainsOrUserPrenomContains(String nom, String prenom, int page,
			int size) {
		return membreJuryDao.findByUserNomContainsOrUserPrenomContains(nom, prenom, PageRequest.of(page, size));
	}

	@Override
	public ResponseEntity<List<MembreJury>> searchForJuries(Specification<MembreJury> spec) {
		return new ResponseEntity<>(membreJuryDao.findAll(Specification.where(spec)), HttpStatus.OK);
	}

}
