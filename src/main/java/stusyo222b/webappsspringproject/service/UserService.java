package stusyo222b.webappsspringproject.service;

import stusyo222b.webappsspringproject.entities.User;

import java.util.List;

public interface UserService {
    User findByName(String name);
    List<User> findAllUsers();
    void saveUser(User user);
}
