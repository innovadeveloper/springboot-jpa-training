package com.training.shell.trainingjpa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDTO {
    private String name;
    private long courseId;
    private long universityId;
}
