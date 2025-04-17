package tn.ucar.enicar.info.projetspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ucar.enicar.info.projetspring.entities.Team;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByEventId(Long eventId);
}
