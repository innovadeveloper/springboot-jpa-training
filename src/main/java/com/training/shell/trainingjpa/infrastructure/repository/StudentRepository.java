package com.training.shell.trainingjpa.infrastructure.repository;

import com.training.shell.trainingjpa.domain.dto.StudentCourseDTO;
import com.training.shell.trainingjpa.domain.dto.StudentNameDTO;
import com.training.shell.trainingjpa.infrastructure.model.StudentEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query("SELECT s FROM StudentEntity s")
    List<StudentEntity> findAll();

    @Query("SELECT s FROM StudentEntity s JOIN FETCH s.course c JOIN FETCH c.university")
    List<StudentEntity> findAllFullFetch();

    @Query("SELECT new com.training.shell.trainingjpa.domain.dto.StudentCourseDTO(s.name, s.course) FROM StudentEntity s JOIN FETCH s.course c")
    List<StudentCourseDTO> findAllFullFetch2();

    @Query("SELECT s FROM StudentEntity s WHERE s.name LIKE %:name%")
    List<StudentEntity> findByName(@Param("name") String name);

    @Query("SELECT s FROM StudentEntity s JOIN FETCH s.course WHERE s.name LIKE %:name%")
    List<StudentEntity> findByNameFullFetch(@Param("name") String name);

    // TODO: Si ya se tiene una relaciòn entre dos tablas, para realizar un join se debe aprovechar la propiedad relacionada del modelo y
    // todo: no llamar de forma explicita a la tabla...
//    Error -> @Query("SELECT new com.training.shell.trainingjpa.domain.dto.StudentNameDTO(s.name, c.name) FROM StudentEntity s JOIN CourseEntity c WHERE s.name LIKE %:name%" +
//            " AND c.name =:universityName")
    @Query("SELECT new com.training.shell.trainingjpa.domain.dto.StudentNameDTO(s.name, c.name) FROM StudentEntity s JOIN s.course c JOIN c.university u WHERE s.name LIKE %:name%" +
            " AND u.name =:universityName")
    List<StudentNameDTO> findCustomStudentByName(@Param("name") String name, @Param("universityName") String universityName);

    // todo : query por group by
    //  @Query("SELECT i FROM Item i LEFT JOIN i.reviews r GROUP BY i.id HAVING COALESCE(AVG(r.rating), 0) < :rating")
}
