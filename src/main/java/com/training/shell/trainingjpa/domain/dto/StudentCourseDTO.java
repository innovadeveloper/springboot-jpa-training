package com.training.shell.trainingjpa.domain.dto;

import com.training.shell.trainingjpa.infrastructure.model.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentCourseDTO {
    private String name;
    private CourseEntity course;
}
