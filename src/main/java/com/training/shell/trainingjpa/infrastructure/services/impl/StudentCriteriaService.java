package com.training.shell.trainingjpa.infrastructure.services.impl;

import com.training.shell.trainingjpa.infrastructure.model.CourseEntity;
import com.training.shell.trainingjpa.infrastructure.model.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCriteriaService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * busqueda sobre una entidad con el uso de join para realizar
     * consulta dinàmica.. tmbn se aplicó Fetch al "Course"
     * @param courseName
     * @return
     */
    public List<StudentEntity> searchStudentWithCourse(String courseName){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<StudentEntity> criteriaQuery = builder.createQuery(StudentEntity.class);
        Root<StudentEntity> student = criteriaQuery.from(StudentEntity.class);
        student.fetch("course", JoinType.INNER); // 'course' es el atributo de StudentEntity

        Join<StudentEntity, CourseEntity> course = student.join("course"); // 'course' es el atributo de StudentEntity
        Predicate coursePredicate = builder.equal(course.get("name"), courseName);
        Predicate studentPredicate = builder.like(student.get("name"), "%s%");
//        criteriaQuery.where(studentPredicate);

        criteriaQuery.where( builder.and(studentPredicate, coursePredicate) );  // multiples predicates sobre el criteriaQuery
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * busqueda con criteria básica.. se carga la propiedad course a pesar q es lazy
     * con el mètodo "fetch"
     * @param letterInName
     * @return
     */
    public List<StudentEntity> searchAllStudentsByName(String letterInName){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<StudentEntity> criteriaQuery = builder.createQuery(StudentEntity.class);
        Root<StudentEntity> student = criteriaQuery.from(StudentEntity.class);
        student.fetch("course", JoinType.INNER); // 'course' es el atributo de StudentEntity
        Predicate studentPredicate = builder.like(student.get("name"), "%s%");
        criteriaQuery.where(studentPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
