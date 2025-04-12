package tn.ucar.enicar.info.projetspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import tn.ucar.enicar.info.projetspring.auth.AuthenticationService;
import tn.ucar.enicar.info.projetspring.auth.RegisterRequest;
import tn.ucar.enicar.info.projetspring.entities.Role;
import tn.ucar.enicar.info.projetspring.repositories.userRepo;


@SpringBootApplication
public class ProjetspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetspringApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service,
            userRepo userRepository
    ) {
        return args -> {
            // Check if the admin already exists
            boolean adminExists = userRepository.findByEmail("admin@mail.com").isPresent();

            if (!adminExists) {
                var admin = RegisterRequest.builder()
                        .firstname("Admin")
                        .lastname("Admin")
                        .email("admin@mail.com")
                        .password("password")
                        .role(Role.ADMIN)
                        .build();
                System.out.println("Admin token: " + service.register(admin).getAccessToken());
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }

}