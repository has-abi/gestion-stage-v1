package com.gestion.stage.rest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.User;
import com.gestion.stage.service.facade.FileStorageService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.ResponseMessage;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
@RequestMapping("gestion-stage-api/user")
@CrossOrigin({"http://localhost:4200"})
public class UserRest {
	@Autowired
	private UserService userService;
	@GetMapping("/email/{email}")
	public User findByEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}
	@GetMapping("/count")
	public int countusers() {
		return userService.countusers();
	}
	@GetMapping("/page/{page}/size/{size}/sort/{sort}")
	public Page<User> findAllWithPagination(@PathVariable int page,@PathVariable int size,@PathVariable String sort) {
		return userService.findAllWithPagination(page, size, sort);
	}
	@GetMapping("/search")
	public ResponseEntity<List<User>> searchForUsers(@SearchSpec Specification<User> spec) {
		return userService.searchForUsers(spec);
	}
	@Autowired
	private FileStorageService fileStorageService;
	@GetMapping("/reference/{reference}")
	public User findByReference(@PathVariable String reference) {
		return userService.findByReference(reference);
	}
	@PutMapping("/photo")
	public ResponseEntity<ResponseMessage> uploadProfilePic(@RequestParam("ref") String ref, @RequestParam("file") MultipartFile file) {
		return userService.uploadProfilePic(ref, file);
	}
	public ResponseEntity<Resource> loadImage(String filename) {
		return userService.loadImage(filename);
	}
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
	@GetMapping("/image/{image}")
	public ResponseEntity<InputStreamResource> getImage(@PathVariable String image) throws IOException {
		Resource imgFile = fileStorageService.loadPics(image);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
}
