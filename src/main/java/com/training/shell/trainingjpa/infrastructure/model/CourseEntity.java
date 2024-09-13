package com.training.shell.trainingjpa.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "Courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    /**
     * @JoinColumn(name = "university_id", referencedColumnName = "id"):
     * name = "university_id": Especifica el nombre de la columna en la tabla Courses que se utiliza para la clave externa.
     * referencedColumnName = "id": Especifica el nombre de la columna en la tabla University que se está referenciando para la relación.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    private UniversityEntity university;

    public CourseEntity(long id) {
        this.id = id;
    }

    public CourseEntity() {

    }
}
