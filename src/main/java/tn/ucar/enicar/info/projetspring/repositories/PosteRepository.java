package tn.ucar.enicar.info.projetspring.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.ucar.enicar.info.projetspring.entities.Poste;
import java.util.List;
@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {


    List<Poste> findByEventId(Long eventId);
    @Query("SELECT DISTINCT p FROM Poste p JOIN FETCH p.competences WHERE p.event.id = :eventId")
    List<Poste> findByEventIdWithCompetences(@Param("eventId") Long eventId);
}
