package tn.ucar.enicar.info.projetspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.ucar.enicar.info.projetspring.entities.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

     @Query("""
    select t from Token t inner join User u on t.user.id = u.id
    where u.id =:userId and (t.expired=false or t.revoked =false)
    """)
    List<Token> findAllValidTokensByUser(long userId);


     Optional<Token> findByToken(String token);

}
