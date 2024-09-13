package com.training.shell.trainingjpa.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "University")
public class UniversityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
