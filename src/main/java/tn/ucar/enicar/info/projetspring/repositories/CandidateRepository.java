package tn.ucar.enicar.info.projetspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ucar.enicar.info.projetspring.entities.Candidate;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByUser_Id(Long userId);


    List<Candidate> findByPoste_Id(Long posteId);


    boolean existsByUser_IdAndPoste_Id(Long userId, Long posteId);
}

