package stusyo222b.webappsspringproject.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stusyo222b.webappsspringproject.entities.User;
import stusyo222b.webappsspringproject.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsServiceConfigDB implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsServiceConfigDB(UserService userService) {
        this.userService = userService;
    }

//    This @Bean is already declared in the configuration file,
// created for use by "built-in" users (CustomUserDetailServiceConfig class)
// It must be commented out in one of the places:
// either here or in CustomUserDetailServiceConfig

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        } else {
            List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
            listAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                    true, true, true, true, listAuthorities);
        }
    }

}

