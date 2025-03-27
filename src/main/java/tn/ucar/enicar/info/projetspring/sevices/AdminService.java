package tn.ucar.enicar.info.projetspring.sevices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ucar.enicar.info.projetspring.auth.AuthenticationResponse;
import tn.ucar.enicar.info.projetspring.auth.AuthenticationService;
import tn.ucar.enicar.info.projetspring.auth.RegisterRequest;
import tn.ucar.enicar.info.projetspring.entities.Role;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AuthenticationService authService;

    public AuthenticationResponse createManager(RegisterRequest request) {
        request.setRole(Role.MANAGER);
        return authService.register(request);
    }
}
