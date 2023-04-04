package com.aca.myprojectaca;

import com.aca.myprojectaca.entity.ERole;
import com.aca.myprojectaca.entity.Role;
import com.aca.myprojectaca.entity.User;
import com.aca.myprojectaca.repository.RoleRepository;
import com.aca.myprojectaca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class MyprojectAcaApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyprojectAcaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Set<Role> roles = new HashSet<>();
		Role roleAdmin = roleRepository.findByRole(ERole.ROLE_ADMIN).orElseThrow();
		roles.add(roleAdmin);


		Optional<User> userAdmin = userRepository.findByUsername("ARTUR");
		if (userAdmin.isEmpty()) {
			User user = new User();
			user.setUsername("ARTUR");
			user.setPassword(passwordEncoder.encode("200685"));
			user.setRoles(roles);
			userRepository.save(user);
		}
	}
}
