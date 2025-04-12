package tn.ucar.enicar.info.projetspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.ucar.enicar.info.projetspring.dto.EventDto;
import tn.ucar.enicar.info.projetspring.dto.ManagerAssignmentDto;
import tn.ucar.enicar.info.projetspring.entities.event;
import tn.ucar.enicar.info.projetspring.sevices.EventService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class eventController {
    private final EventService eventService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createEvent(@RequestBody EventDto eventDto) {
        try {
            if (eventDto.getTitle() == null || eventDto.getTitle().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("Le titre est requis"));
            }
            if (eventDto.getStartDate() == null || eventDto.getEndDate() == null) {
                return ResponseEntity.badRequest().body(createErrorResponse("Les dates sont requises"));
            }

            if (eventDto.getEndDate().before(eventDto.getStartDate())) {
                return ResponseEntity.badRequest().body(
                        createErrorResponse("La date de fin doit être après la date de début")
                );
            }

            if (eventDto.getStartDate().before(new Date())) {
                return ResponseEntity.badRequest().body(
                        "La date de début doit être aujourd'hui ou dans le futur"
                );
            }

            event event = new event();
            event.setTitle(eventDto.getTitle());
            event.setDescription(eventDto.getDescription());
            event.setLocation(eventDto.getLocation());
            event.setStartDate(eventDto.getStartDate());
            event.setEndDate(eventDto.getEndDate());

            event createdEvent = eventService.createEvent(event);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Événement créé avec succès");
            response.put("data", createdEvent);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    createErrorResponse("Erreur serveur: " + e.getMessage())
            );
        }
    }


    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("success", "false");
        response.put("message", message);
        return response;
    }
    @GetMapping
    public ResponseEntity<List<event>> getAllEvents() {
        List<event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
    @GetMapping("/{id}")
    public ResponseEntity<event> getEvent(@PathVariable Long id) {
        return eventService.getEventWithDetails(id)
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