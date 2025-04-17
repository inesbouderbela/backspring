package tn.ucar.enicar.info.projetspring.sevices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.ucar.enicar.info.projetspring.auth.AuthenticationResponse;
import tn.ucar.enicar.info.projetspring.auth.AuthenticationService;
import tn.ucar.enicar.info.projetspring.auth.RegisterRequest;
import tn.ucar.enicar.info.projetspring.entities.Role;
import tn.ucar.enicar.info.projetspring.entities.User;
import tn.ucar.enicar.info.projetspring.repositories.userRepo;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final userRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AuthenticationResponse createManager(RegisterRequest request) {
        // Génération d'un mot de passe aléatoire
        String rawPassword = UUID.randomUUID().toString().substring(0, 8);

        var manager = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(rawPassword))
                .role(Role.MANAGER)
                .build();

        userRepository.save(manager);

        // Envoi d'email avec le mot de passe en clair
        emailService.sendCredentials(request.getEmail(), rawPassword);

        return AuthenticationResponse.builder()
                .build();

    }
}
