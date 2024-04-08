package com.example.document_flow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date createdAt;
    private String createdBy;
    private boolean isApproved;
    private String approvedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;


    public Report(Long id, String title, String description, Date createdAt, String createdBy, boolean isApproved, String approvedBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.isApproved = isApproved;
        this.approvedBy = approvedBy;
    }
}
