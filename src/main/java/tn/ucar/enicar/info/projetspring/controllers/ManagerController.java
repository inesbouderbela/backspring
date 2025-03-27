package tn.ucar.enicar.info.projetspring.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class ManagerController {
    @GetMapping
    @PreAuthorize("hasAuthority('manager:read')")
    public String get() {
        return "GET:: manager controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('manager:create')")
    public String post() {
        return "POST:: manager controller";
    }
    @PutMapping
    @PreAuthorize("hasAuthority('manager:update')")
    public String put() {
        return "PUT:: manager controller";
    }

}
