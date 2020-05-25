package com.gestion.stage.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.User;
import com.gestion.stage.service.facade.UserService;

@RestController
@RequestMapping("gestion-stage-api/user")
@CrossOrigin({"http://localhost:4200"})
public class UserRest {
	@Autowired
	private UserService userService;
	@GetMapping("/dateNaissance/{dateNaissance}")
	public List<User> findByDateNaissanceGreaterThan(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date dateNaissance) {
		return userService.findByDateNaissanceGreaterThan(dateNaissance);
	}
	@GetMapping("/nom/{nom}")
	public List<User> findByNomContains(@PathVariable String nom) {
		return userService.findByNomContains(nom);
	}
	@GetMapping("/prenom/{prenom}")
	public List<User> findByPrenomContains(@PathVariable String prenom) {
		return userService.findByPrenomContains(prenom);
	}
	@GetMapping("/dateJoin/{dateJoin}")
	public List<User> findByDateJoin(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date dateJoin) {
		return userService.findByDateJoin(dateJoin);
	}
	@PostMapping("/login")
	public int login(@RequestBody User user) {
		return userService.login(user);
	}
	@PostMapping("/register")
	public int register(@RequestBody User user) {
		return userService.register(user);
	}
	@PutMapping("/")
	public int update(@RequestBody User user) {
		return userService.update(user);
	}
	@DeleteMapping("/id/{id}")
	public int removeById(@PathVariable Long id) {
		return userService.removeById(id);
	}
	@GetMapping("/")
	public List<User> findAll() {
		return userService.findAll();
	}
}
