package com.training.shell.trainingjpa.infrastructure.services.impl;

import com.training.shell.trainingjpa.domain.dto.StudentDTO;
import com.training.shell.trainingjpa.infrastructure.model.CourseEntity;
import com.training.shell.trainingjpa.infrastructure.model.StudentEntity;
import com.training.shell.trainingjpa.infrastructure.model.UniversityEntity;
import com.training.shell.trainingjpa.infrastructure.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // Obtener todos los estudiantes
    public List<StudentDTO> getAllStudents() {
//        List<StudentEntity> students = studentRepository.findAll();
        List<StudentEntity> students = studentRepository.findAllFullFetch();

        return students.stream()
                .map(student -> new StudentDTO(student.getName(), student.getCourse().getId(), student.getUniversity().getId()))
                .collect(Collectors.toList());
    }

    // Obtener un estudiante por ID
    public StudentDTO getStudentById(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return new StudentDTO(student.getName(), student.getCourse().getId(), student.getUniversity().getId());
    }

    // Crear un nuevo estudiante
    public StudentDTO createStudent(StudentDTO studentDTO) {
        StudentEntity student = new StudentEntity();
        student.setName(studentDTO.getName());
        // Suponiendo que se carguen las entidades relacionadas (course y university)
        student.setCourse(new CourseEntity(studentDTO.getCourseId()));
        student.setUniversity(new UniversityEntity(studentDTO.getUniversityId()));
        studentRepository.save(student);
        return new StudentDTO(student.getName(), student.getCourse().getId(), student.getUniversity().getId());
    }

    // Actualizar un estudiante
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        student.setName(studentDTO.getName());
        student.setCourse(new CourseEntity(studentDTO.getCourseId()));
        student.setUniversity(new UniversityEntity(studentDTO.getUniversityId()));
        studentRepository.save(student);
        return new StudentDTO(student.getName(), student.getCourse().getId(), student.getUniversity().getId());
    }

    // Eliminar un estudiante
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // Actualización parcial de un estudiante (solo actualizar los campos que se especifican)
    public StudentDTO partialUpdateStudent(Long id, Map<String, Object> updates) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        if (updates.containsKey("name")) {
            student.setName((String) updates.get("name"));
        }

        studentRepository.save(student);
        return new StudentDTO(student.getName(), student.getCourse().getId(), student.getUniversity().getId());
    }
}
