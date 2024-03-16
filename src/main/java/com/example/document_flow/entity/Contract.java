package com.example.document_flow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "contracts")
@Getter
@Setter
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
//    private Person person;

    public Contract(Long id, String clientName, Person person) {
        this.id = id;
        this.clientName = clientName;
//        this.person = person;
    }
}
