package com.training.shell.trainingjpa.infrastructure.services.impl;

import com.training.shell.trainingjpa.infrastructure.model.StudentEntity;
import com.training.shell.trainingjpa.infrastructure.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    @Transactional
    public void createNewStudent(StudentEntity... students){
        for (StudentEntity student : students) {
            StudentEntity studentEntity = studentRepository.save(student);
            log.info("student registered");
        }
        log.info("students created");
    }

}
