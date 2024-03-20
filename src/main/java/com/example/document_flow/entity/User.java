package com.example.document_flow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private Permission permission;

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    public User(Long id, String login, String password, Permission permission, Person person) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.permission = permission;
        this.person = person;
    }

}
