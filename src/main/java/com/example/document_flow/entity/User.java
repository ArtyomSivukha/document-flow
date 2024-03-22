package com.example.document_flow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Document> documents;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Notification> notifications;

    public User(Long id, String login, String password, Permission permission, Person person) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.permission = permission;
        this.person = person;
    }

}
