package com.training.shell.trainingjpa.infrastructure.repository;

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

    @Query("SELECT s FROM StudentEntity s WHERE s.name LIKE %:name%")
    List<StudentEntity> findByName(@Param("name") String name);

    @Query("SELECT s FROM StudentEntity s JOIN FETCH s.course WHERE s.name LIKE %:name%")
    List<StudentEntity> findByNameFullFetch(@Param("name") String name);

}
