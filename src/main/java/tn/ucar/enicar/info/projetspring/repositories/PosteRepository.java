package tn.ucar.enicar.info.projetspring.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import tn.ucar.enicar.info.projetspring.entities.Poste;
import java.util.List;

public interface PosteRepository extends JpaRepository<Poste, Long> {


    List<Poste> findByEventId(Long eventId);
}
