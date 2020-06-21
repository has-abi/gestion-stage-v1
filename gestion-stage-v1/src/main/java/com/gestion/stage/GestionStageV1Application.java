package com.gestion.stage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gestion.stage.bean.User;
import com.gestion.stage.dao.UserDao;
import com.gestion.stage.service.facade.UserService;


@SpringBootApplication 
//(exclude = SecurityAutoConfiguration.class)
public class GestionStageV1Application implements CommandLineRunner{
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(GestionStageV1Application.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() { 
		return new BCryptPasswordEncoder();
		}
	@Override
	public void run(String... args) throws Exception {
		userService.register(new User(null, "U223111", "userTest", "userT", null, null, null, null, "test@test.com", "test", null, false, null, null, null, null));
		userService.register(new User(null, "U223112", "userAdmin", "Admin", null, null, null, null, "admin@test.com", "admin", null, false, null, null, null, null));
		List<User> users = this.userService.findAll();
		users.forEach(user->{
			if(user.getPassword() != null) {
				user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
				this.userDao.save(user);
			}
			
		});
	}
}
