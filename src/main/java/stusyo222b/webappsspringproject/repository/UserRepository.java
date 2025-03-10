package stusyo222b.webappsspringproject.repository;

import stusyo222b.webappsspringproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);
}