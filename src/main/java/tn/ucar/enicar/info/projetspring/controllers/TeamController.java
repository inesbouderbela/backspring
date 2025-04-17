package tn.ucar.enicar.info.projetspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ucar.enicar.info.projetspring.entities.Team;
import tn.ucar.enicar.info.projetspring.entities.event;
import tn.ucar.enicar.info.projetspring.repositories.EventRepository;
import tn.ucar.enicar.info.projetspring.repositories.TeamRepository;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class TeamController {

    private final EventRepository eventRepository;
    private final TeamRepository teamRepository;

    @PostMapping("/{eventId}/teams")
    public ResponseEntity<Team> addTeamToEvent(@PathVariable Long eventId, @RequestBody Team teamRequest) {
        event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        teamRequest.setEvent(event);
        teamRequest.setNb_indiv(0L);
        teamRequest.setTeamLeader(null);

        Team savedTeam = teamRepository.save(teamRequest);

        return ResponseEntity.ok(savedTeam);
    }
}
