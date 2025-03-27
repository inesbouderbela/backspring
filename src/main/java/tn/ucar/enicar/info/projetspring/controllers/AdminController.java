package tn.ucar.enicar.info.projetspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.ucar.enicar.info.projetspring.auth.AuthenticationResponse;
import tn.ucar.enicar.info.projetspring.auth.RegisterRequest;
import tn.ucar.enicar.info.projetspring.sevices.AdminService;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor // Ajoutez cette annotation
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService; // Gardez cette déclaration

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public String post() {
        return "POST:: admin controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String put() {
        return "PUT:: admin controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public String delete() {
        return "DELETE:: admin controller";
    }

    @PostMapping("/create-manager")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<AuthenticationResponse> createManager(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(adminService.createManager(request));
    }
}