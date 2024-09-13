package com.training.shell.trainingjpa;

import com.training.shell.trainingjpa.infrastructure.model.CourseEntity;
import com.training.shell.trainingjpa.infrastructure.model.StudentEntity;
import com.training.shell.trainingjpa.infrastructure.model.UniversityEntity;
import com.training.shell.trainingjpa.infrastructure.repository.StudentRepository;
import com.training.shell.trainingjpa.infrastructure.services.impl.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class TrainingJpaApplication implements ApplicationRunner {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(TrainingJpaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<StudentEntity> studentList = studentRepository.findAll();
        List<StudentEntity> studentEntityListByName = studentRepository.findByName("s");
        List<StudentEntity> studentEntityListFullByName = studentRepository.findByNameFullFetch("pedro");

        UniversityEntity university = new UniversityEntity();
        university.setName("upn");

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(4);
        courseEntity.setName("something");
        courseEntity.setUniversity(university);

        StudentEntity firstStudentEntity = new StudentEntity();
        firstStudentEntity.setName("failed");
        firstStudentEntity.setCourse(courseEntity);
        firstStudentEntity.setUniversity(university);

//        ##########################################
        UniversityEntity universitySuccess = new UniversityEntity();
        universitySuccess.setId(1);

        CourseEntity courseEntitySuccess = new CourseEntity();
        courseEntitySuccess.setId(1);
        courseEntitySuccess.setUniversity(universitySuccess);

        StudentEntity firstStudentEntitySuccess = new StudentEntity();
        firstStudentEntitySuccess.setName("pedro A" + new Date().getSeconds());
        firstStudentEntitySuccess.setCourse(courseEntitySuccess);
        firstStudentEntitySuccess.setUniversity(universitySuccess);
//        ##########################################
        try {
            studentService.createNewStudent(firstStudentEntitySuccess, firstStudentEntity);
            log.info("success ");
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("result: {}", studentEntityListByName.size());
    }
}
