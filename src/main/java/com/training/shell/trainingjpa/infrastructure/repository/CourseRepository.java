package com.training.shell.trainingjpa.infrastructure.repository;

import com.training.shell.trainingjpa.infrastructure.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
