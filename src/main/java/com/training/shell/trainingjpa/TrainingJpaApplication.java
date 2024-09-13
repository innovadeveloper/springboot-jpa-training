package com.training.shell.trainingjpa;

import com.training.shell.trainingjpa.infrastructure.model.CourseEntity;
import com.training.shell.trainingjpa.infrastructure.model.StudentEntity;
import com.training.shell.trainingjpa.infrastructure.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class TrainingJpaApplication implements ApplicationRunner {

    private final StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(TrainingJpaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<StudentEntity> studentList = studentRepository.findAll();
        List<StudentEntity> studentEntityListByName = studentRepository.findByName("s");
        List<StudentEntity> studentEntityListFullByName = studentRepository.findByNameFullFetch("s");
        log.info("result: {}", studentEntityListByName.size());
    }
}
