package tn.ucar.enicar.info.projetspring.controllers;

import io.jsonwebtoken.io.IOException;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.ucar.enicar.info.projetspring.dto.EventCreateDTO;

import tn.ucar.enicar.info.projetspring.dto.EventDto;
import tn.ucar.enicar.info.projetspring.dto.ManagerAssignmentDto;
import tn.ucar.enicar.info.projetspring.entities.User;
import tn.ucar.enicar.info.projetspring.entities.event;
import tn.ucar.enicar.info.projetspring.repositories.userRepo;
import tn.ucar.enicar.info.projetspring.sevices.EventService;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class eventController {
    private final EventService eventService;
    private final userRepo userRepository;

    @PreAuthorize("hasRole('ADMIN')")


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createEvent(
            @RequestPart("event") EventCreateDTO eventDTO,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            @AuthenticationPrincipal User admin) {

        try {

            eventDTO.setImageFile(imageFile);

            event createdEvent = eventService.createEvent(eventDTO, admin.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors du traitement de l'image");
        }
    }
/*
    @GetMapping("/{id}/image")
    public ResponseEntity<?> getEventImage(@PathVariable Long id) {
        event event = eventService.getEventById(id); // Lance une exception si non trouvé

        if (event.getImagePath() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .body(Map.of("imageUrl", event.getImageUrl()));
    }*/




    @GetMapping("/uploads/events/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws MalformedURLException {
        Path file = Paths.get("uploads/events/" + filename);
        UrlResource resource = new UrlResource(file.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body((Resource) resource);
    }


    @GetMapping
    public ResponseEntity<List<event>> getAllEvents() {
        List<event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
    @GetMapping("/{id}")
    public ResponseEntity<event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
/*
    @PatchMapping("/{eventId}/manager")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<event> assignManager(
            @PathVariable String eventId,
            @RequestBody ManagerAssignmentDto assignment) {

        event event = eventService.assignManager(eventId, assignment.getManagerId());
        return ResponseEntity.ok(event);
    }*/
}