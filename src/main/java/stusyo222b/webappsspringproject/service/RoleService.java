package stusyo222b.webappsspringproject.service;

import stusyo222b.webappsspringproject.entities.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);

    void save(Role role);

    List<Role> findAll();
}