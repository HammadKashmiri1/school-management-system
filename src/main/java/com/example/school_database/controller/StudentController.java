package com.example.school_database.controller;

import com.example.school_database.model.Student;
import com.example.school_database.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "APIs for managing students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Operation(summary = "Get all students", description = "Retrieve a list of all students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID", description = "Retrieve a specific student by their ID")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student with id " + id + " not found"));
    }

    @PostMapping
    @Operation(summary = "Create a new student", description = "Create a new student with the provided details")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update student", description = "Update an existing student with the provided details")
    public ResponseEntity<Student> updateStudent(
            @PathVariable String id,
            @RequestBody Student updatedStudent) {
        
        return studentService.getStudentById(id)
                .map(existingStudent -> {
                    existingStudent.setFirstName(updatedStudent.getFirstName());
                    existingStudent.setLastName(updatedStudent.getLastName());
                    existingStudent.setAge(updatedStudent.getAge());
                    existingStudent.setSchoolId(updatedStudent.getSchoolId());
                    existingStudent.setClassId(updatedStudent.getClassId());
                    Student savedStudent = studentService.saveStudent(existingStudent);
                    return ResponseEntity.ok(savedStudent);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student with id " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student", description = "Delete a student by their ID")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/school/{schoolId}")
    @Operation(summary = "Get students by school", description = "Retrieve all students from a specific school")
    public ResponseEntity<List<Student>> getStudentsBySchool(@PathVariable String schoolId) {
        List<Student> students = studentService.getStudentsBySchool(schoolId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/class/{classId}")
    @Operation(summary = "Get students by class", description = "Retrieve all students from a specific class")
    public ResponseEntity<List<Student>> getStudentsByClass(@PathVariable String classId) {
        List<Student> students = studentService.getStudentsByClass(classId);
        return ResponseEntity.ok(students);
    }
} 