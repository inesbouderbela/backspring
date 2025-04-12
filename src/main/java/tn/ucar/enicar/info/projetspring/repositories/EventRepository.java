package tn.ucar.enicar.info.projetspring.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ucar.enicar.info.projetspring.entities.event;


import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<event, Long> {
    @EntityGraph(attributePaths = {"manager", "postes", "volunteers"})
    Optional<event> findWithDetailsById(Long id);
    Optional<event> findById(Long id);
    Optional<event> findByTitle(String title);
}
