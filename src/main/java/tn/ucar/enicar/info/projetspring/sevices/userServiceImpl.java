package tn.ucar.enicar.info.projetspring.sevices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ucar.enicar.info.projetspring.entities.user;
import tn.ucar.enicar.info.projetspring.repositories.userRepo;

import java.util.List;
@Service
@AllArgsConstructor
public class userServiceImpl implements userService {
    userRepo userRepo;

    public List<user> retrieveAllUsers() {
        return userRepo.findAll();
    }
}





