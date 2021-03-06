package com.arif.springsecuritywithjwt;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.arif.springsecuritywithjwt.model.Role;
import com.arif.springsecuritywithjwt.model.User;
import com.arif.springsecuritywithjwt.service.UserService;

@SpringBootApplication
public class SpringSecurityWithJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityWithJwtApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role("ROLE_USER"));
			userService.saveRole(new Role("ROLE_MANAGER"));
			userService.saveRole(new Role("ROLE_ADMIN"));
			userService.saveRole(new Role("ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new User("Arif", "arif", "1234", new ArrayList<>()));
			userService.saveUser(new User("Labib", "labib", "1234", new ArrayList<>()));
			userService.saveUser(new User("Moin", "moin", "1234", new ArrayList<>()));
			userService.saveUser(new User("Ashraful", "ashraful", "123456", new ArrayList<>()));
			
			userService.addRoleToUser("arif", "ROLE_USER");
			userService.addRoleToUser("arif", "ROLE_MANAGER");
			userService.addRoleToUser("labib", "ROLE_MANAGER");
			userService.addRoleToUser("moin", "ROLE_ADMIN");
			userService.addRoleToUser("ashraful", "ROLE_ADMIN");
			userService.addRoleToUser("ashraful", "ROLE_SUPER_ADMIN");
		};
	}

}
