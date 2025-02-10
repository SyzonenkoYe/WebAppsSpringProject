package stusyo222b.webappsspringproject.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.ArrayList;
import java.util.List;

//https://www.javaguides.net/2018/10/user-registration-module-using-springboot-springmvc-springsecurity-hibernate5-thymeleaf-mysql.html
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nickname!!!!!!
    @Column(nullable=false)
    @Check(constraints = "REGEXP_LIKE(name, " +
            "'^[a-zA-Z]([\\\\.\\\\-$]?[a-zA-Z\\\\d]+){1,3}$','c') = 1")
    private String name;

    //8-20 symbols
    @Column(nullable=false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}