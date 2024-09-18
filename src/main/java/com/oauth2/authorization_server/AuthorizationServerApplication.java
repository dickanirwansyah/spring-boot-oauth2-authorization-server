package com.oauth2.authorization_server;

import com.oauth2.authorization_server.entity.Roles;
import com.oauth2.authorization_server.enums.RolesName;
import com.oauth2.authorization_server.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}
}

@Slf4j
@Component
@RequiredArgsConstructor
class InitializeData implements CommandLineRunner {

	private final RolesRepository rolesRepository;

	@Override
	public void run(String... args) throws Exception {
		if (rolesRepository.findAll().isEmpty()){
			log.info("process initialize roles..");
			Roles rolesAdmin = new Roles();
			rolesAdmin.setRoleName(RolesName.ROLE_ADMIN.name());
			rolesRepository.save(rolesAdmin);

			Roles rolesUser = new Roles();
			rolesUser.setRoleName(RolesName.ROLE_USER.name());
			rolesRepository.save(rolesUser);
		}
	}
}
