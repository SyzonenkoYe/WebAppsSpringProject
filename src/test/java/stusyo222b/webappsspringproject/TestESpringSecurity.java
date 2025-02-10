package stusyo222b.webappsspringproject;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import stusyo222b.webappsspringproject.entities.Role;
import stusyo222b.webappsspringproject.entities.User;
import stusyo222b.webappsspringproject.service.RoleService;
import stusyo222b.webappsspringproject.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class TestESpringSecurity {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
    }

    @Test
    void insertUsersRolesTest(){
        //============= add roles if its not on db
        Role adminR = roleService.findByName("ROLE_ADMIN");
        if (adminR == null) {
            adminR = new Role(1L, "ROLE_ADMIN");
            roleService.save(adminR);
            adminR = roleService.findByName("ROLE_ADMIN");
        }

        Role userR = roleService.findByName("ROLE_USER");
        if (userR == null) {
            userR = new Role(2L, "ROLE_USER");
            roleService.save(userR);
            userR = roleService.findByName("ROLE_USER");
        }

        assertEquals(2, roleService.findAll().size());

        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

        User userAdmin = userService.findByName("adminDB");
        if (userAdmin == null) {
            userAdmin = new User();
            userAdmin.setName("adminDB");
            userAdmin.setPassword(passwordEncoder.encode("adminAAA"));
            userAdmin.setRole(adminR);
            userService.saveUser(userAdmin);
            userAdmin = userService.findByName("adminDB");
        }

        assertNotEquals(userAdmin.getRole(),null);

        User user = userService.findByName("testDBuser");
        if (user == null) {
            user = new User();
            user.setName("testDBuser");
            user.setPassword(passwordEncoder.encode("userAAA"));
            user.setRole(userR);
            userService.saveUser(user);
            user = userService.findByName("testDBuser");
        }
        assertNotEquals(user.getRole(),null);

    }


}
