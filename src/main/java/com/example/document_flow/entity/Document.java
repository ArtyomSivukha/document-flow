package com.example.document_flow.entity;

import com.example.document_flow.entity.enums.DocumentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    private String filePath;
    private String type;
    private DocumentType documentType;

//    @Lob
//    @Basic(fetch = FetchType.EAGER)
//    private byte[] content;


    @JsonIgnore
    @ManyToOne (cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private User author;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<User> shares = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Project> projects;


    public Document(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }
}