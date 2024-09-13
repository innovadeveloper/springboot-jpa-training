package com.training.shell.trainingjpa.ui.controllers;


import com.training.shell.trainingjpa.domain.dto.StudentCourseDTO;
import com.training.shell.trainingjpa.domain.dto.StudentDTO;
import com.training.shell.trainingjpa.infrastructure.model.StudentEntity;
import com.training.shell.trainingjpa.infrastructure.repository.StudentRepository;
import com.training.shell.trainingjpa.infrastructure.services.impl.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// http://localhost:9001/students_not_reactive/fetch
@AllArgsConstructor
@RestController
@RequestMapping("/students_not_reactive")
public class StudentNotReactiveController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;


    // 1. GET: Obtener todos los estudiantes
    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/fetch")
    public  ResponseEntity<List<StudentCourseDTO>> getAllStudentsWithFetch() {
        List<StudentCourseDTO> studentListWithFetch = studentRepository.findAllFullFetch2();
        return ResponseEntity.ok(studentListWithFetch);
    }


    // 2. GET: Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // 3. POST: Crear un nuevo estudiante
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    // 4. PUT: Actualizar un estudiante existente
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    // 5. DELETE: Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // 6. PATCH: Actualizar parcialmente un estudiante (por ejemplo, solo el nombre)
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> partialUpdateStudent(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        StudentDTO updatedStudent = studentService.partialUpdateStudent(id, updates);
        return ResponseEntity.ok(updatedStudent);
    }
}
