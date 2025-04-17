package tn.ucar.enicar.info.projetspring.sevices;

import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ucar.enicar.info.projetspring.entities.Poste;
import tn.ucar.enicar.info.projetspring.entities.Team;
import tn.ucar.enicar.info.projetspring.entities.event;
import tn.ucar.enicar.info.projetspring.enums.PosteType;
import tn.ucar.enicar.info.projetspring.repositories.EventRepository;
import tn.ucar.enicar.info.projetspring.repositories.PosteRepository;
import tn.ucar.enicar.info.projetspring.repositories.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PosteService {
    private final PosteRepository posteRepository;
    private final EventRepository eventRepository;
    private final TeamRepository teamRepository;

    public Poste createPoste(Poste poste, Long eventId, Long teamId) {
        event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        poste.setEvent(event);

        if (poste.getType() != PosteType.COORDINATOR) {
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new RuntimeException("Team not found"));
            poste.setTeam(team);
        } else {
            poste.setTeam(null);
        }

        return posteRepository.save(poste);
    }

    public List<Team> getTeamsByEvent(Long eventId) {
        return teamRepository.findByEventId(eventId);
    }
}
