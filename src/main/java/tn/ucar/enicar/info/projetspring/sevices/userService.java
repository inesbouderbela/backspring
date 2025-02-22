package tn.ucar.enicar.info.projetspring.sevices;

import tn.ucar.enicar.info.projetspring.entities.user;

import java.util.List;

public interface userService {
    public List<user> retrieveAllUsers();
}
