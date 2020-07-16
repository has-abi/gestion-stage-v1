package com.gestion.stage.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.Role;
import com.gestion.stage.dao.RoleDao;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("gestion-stage-api/role")
@CrossOrigin({ "http://localhost:4200" })
public class RoleRest {
	@Autowired
	private RoleDao roleDao;

	@GetMapping("/")
	List<Role> findAll() {
		return this.roleDao.findAll();
	}

}
