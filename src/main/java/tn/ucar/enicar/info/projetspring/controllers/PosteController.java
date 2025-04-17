package tn.ucar.enicar.info.projetspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.ucar.enicar.info.projetspring.dto.PosteCreationRequest;
import tn.ucar.enicar.info.projetspring.entities.Poste;
import tn.ucar.enicar.info.projetspring.entities.Team;
import tn.ucar.enicar.info.projetspring.sevices.PosteService;

import java.util.List;

@RestController
@RequestMapping("/api/postes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PosteController {

    private final PosteService posteService;

    @PostMapping("/create")
    public Poste createPoste(@RequestBody PosteCreationRequest request) {
        Poste poste = new Poste();
        poste.setTitle(request.getTitle());
        poste.setDescription(request.getDescription());
        poste.setType(request.getType());
        poste.setStatus(request.getStatus());

        return posteService.createPoste(poste, request.getEventId(), request.getTeamId());
    }

    @GetMapping("/teams/{eventId}")
    public List<Team> getTeamsByEvent(@PathVariable Long eventId) {
        return posteService.getTeamsByEvent(eventId);
    }
}
