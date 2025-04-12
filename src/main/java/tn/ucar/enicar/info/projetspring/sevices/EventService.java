package tn.ucar.enicar.info.projetspring.sevices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.ucar.enicar.info.projetspring.entities.Poste;
import tn.ucar.enicar.info.projetspring.entities.event;
import tn.ucar.enicar.info.projetspring.entities.User;
import tn.ucar.enicar.info.projetspring.repositories.EventRepository;

import tn.ucar.enicar.info.projetspring.repositories.userRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;
    private final userRepo userRepository;
    public List<event> getAllEvents() {
        return eventRepository.findAll();
    }

    public event createEvent(event event) {
        if (event.getEndDate().before(event.getStartDate())) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début");
        }


        event.setManager(null);
        return eventRepository.save(event);
    }

/*
    public event assignResponsibleToEvent(Long eventId, Long responsibleId) {
        event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));

        User responsible = userRepository.findById(responsibleId)
                .orElseThrow(() -> new RuntimeException("Utilisateur responsable non trouvé"));

        // Vérifier que l'utilisateur a bien le rôle RESPONSABLE
        if (!responsible.getRole().equals(Role.RESPONSABLE)) {
            throw new IllegalArgumentException("L'utilisateur doit avoir le rôle RESPONSABLE");
        }

        event.setResponsiblePerson(responsible);
        return eventRepository.save(event);
    }*/


    public Optional<event> getEventById(Long id) {
        return eventRepository.findById(id);
    }
    public Optional<event> getEventWithDetails(Long id) {
        return eventRepository.findWithDetailsById(id);
    }

    public void addPosteToEvent(Long eventId, Poste poste) {
        event event = eventRepository.findById(eventId).orElseThrow();
        poste.setEvent(event);
        event.getPostes().add(poste);
        eventRepository.save(event);
    }

/*
    public List<event> getEventsByResponsible(Long responsibleId) {
        return eventRepository.findByResponsiblePersonId(responsibleId);
    }*/
}