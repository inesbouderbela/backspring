package tn.ucar.enicar.info.projetspring.sevices;
import jdk.jfr.Event;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.ucar.enicar.info.projetspring.dto.EventCreateDTO;

import tn.ucar.enicar.info.projetspring.entities.Poste;
import tn.ucar.enicar.info.projetspring.entities.event;
import tn.ucar.enicar.info.projetspring.entities.User;
import tn.ucar.enicar.info.projetspring.repositories.EventRepository;

import tn.ucar.enicar.info.projetspring.repositories.PosteRepository;
import tn.ucar.enicar.info.projetspring.repositories.userRepo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;
    private final userRepo userRepository;
    private final FileStorageService fileStorageService;
    private final PosteRepository posteRepository;
    private final ModelMapper modelMapper;
    public List<event> getAllEvents() {
        return eventRepository.findAll();
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

/*
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



    public event createEvent(EventCreateDTO dto, String adminEmail) {
        event event = new event();

        // Champs obligatoires
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setStartDate(convertToDate(dto.getStartDate()));
        event.setEndDate(convertToDate(dto.getEndDate()));

        // Champs optionnels
        event.setOrganization(dto.getOrganization());
        event.setTechnology(dto.getTechnology());
        event.setBenefits(dto.getBenefits());
        event.setVision(dto.getVision());

        // Gestion de l'image
        try {
            if (dto.getImageFile() != null && !dto.getImageFile().isEmpty()) {
                String imagePath = fileStorageService.storeFile(dto.getImageFile(), "events");
                event.setImagePath(imagePath);  // Stockage du chemin relatif seulement
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image file: " + e.getMessage(), e);
        }

        // L'admin devient automatiquement manager
        User admin = userRepository.findByEmail(adminEmail)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        event.setGeneralCoordinator(admin);

        return eventRepository.save(event);
    }

/*
    public event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Événement non trouvé"));
    }*/

    public Optional<event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    private Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }



}