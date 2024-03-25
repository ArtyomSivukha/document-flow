package com.example.document_flow.entity;

import com.example.document_flow.entity.enums.Permission;
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
    private List<Document> shareDocuments;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @OneToMany (mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents;

    public User(Long id, String login, String password, Permission permission, Person person) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.permission = permission;
        this.person = person;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", permission=" + permission +
                ", person=" + person +
                ", shareDocuments=" + shareDocuments +
                ", notifications=" + notifications +
                ", documents=" + documents +
                '}';
    }
}
