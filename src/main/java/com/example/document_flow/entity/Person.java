package com.example.document_flow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "persons")
@Setter
@Getter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String patronymic;
    private String secondname;
    private String position;
    private Date birthdate;

    @ManyToOne (cascade = CascadeType.ALL)
    private Department department;

    @OneToOne (cascade = CascadeType.ALL)
    private Contract contract;

    @OneToOne (cascade = CascadeType.ALL)
    private Address address;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Person(Long id, String firstname, String patronymic, String secondname, String position, Date birthdate, Department department) {
        this.id = id;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.secondname = secondname;
        this.position = position;
        this.birthdate = birthdate;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", secondname='" + secondname + '\'' +
                ", position='" + position + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
