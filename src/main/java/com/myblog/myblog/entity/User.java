package com.myblog.myblog.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Data
@Entity
@Table(name = "users" , uniqueConstraints ={ @UniqueConstraint(columnNames = {"username"}), @UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String username;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles" , joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id") ,
    inverseJoinColumns = @JoinColumn(name = "role_id" , referencedColumnName = "id"))

    private Set<Role> roles;
}

