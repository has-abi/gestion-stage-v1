package com.gestion.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gestion.stage.bean.Role;
import com.gestion.stage.bean.User;
import com.gestion.stage.dao.RoleDao;
import com.gestion.stage.service.facade.RoleService;
import com.gestion.stage.service.facade.UserService;

@SpringBootApplication
public class GestionStageV1Application  implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleDao roleDao;

	public static void main(String[] args) {
		SpringApplication.run(GestionStageV1Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUsername("admin@gmail.com");
		user.setPassword("admin");
		user.setNom("admin");
		user.setPrenom("admin");
		Role role = new Role();
		role.setRole("ADMIN_ROLE");
		if(userService.findByUsername(user.getUsername()) == null){
		roleDao.save(role);
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleService.getAdminRole());
		user.setRoles(roles);
		userService.register(user);
		}
		
	}
}
