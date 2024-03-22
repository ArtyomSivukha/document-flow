package com.example.document_flow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "document")
@Getter
@Setter
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    private byte[] content;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Project> projects;

    public Document(Long id, String name, String type, byte[] content) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.content = content;
    }

}