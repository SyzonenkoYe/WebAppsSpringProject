package stusyo222b.webappsspringproject.controller;

import stusyo222b.webappsspringproject.entities.User;
import stusyo222b.webappsspringproject.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String showStartPage() {
        return "WelcomeSpringPage";
    }

    //-------------- LOGIN
    // handler method to handle login request
    @PostMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/login")
    public String loginWithMessage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout,
                                   @RequestParam(value = "sessionExpired", required = false) String sessionExpired,
                                   Model model) {
        return "auth/login";
    }

    //--------   USERS

    // handler method to handle list of users
    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String users(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "auth/users";
    }

}
