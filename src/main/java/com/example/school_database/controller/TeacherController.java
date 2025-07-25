package com.example.school_database.controller;

import com.example.school_database.model.Teacher;
import com.example.school_database.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teacher Management", description = "APIs for managing teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    @Operation(summary = "Get all teachers", description = "Retrieve a list of all teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get teacher by ID", description = "Retrieve a specific teacher by their ID")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String id) {
        return teacherService.getTeacherById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Teacher with id " + id + " not found"));
    }

    @PostMapping
    @Operation(summary = "Create a new teacher", description = "Create a new teacher with the provided details")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update teacher", description = "Update an existing teacher with the provided details")
    public ResponseEntity<Teacher> updateTeacher(
            @PathVariable String id,
            @RequestBody Teacher updatedTeacher) {
        return teacherService.getTeacherById(id)
                .map(existingTeacher -> {
                    existingTeacher.setName(updatedTeacher.getName());
                    existingTeacher.setSubject(updatedTeacher.getSubject());
                    existingTeacher.setSchoolId(updatedTeacher.getSchoolId());
                    Teacher savedTeacher = teacherService.saveTeacher(existingTeacher);
                    return ResponseEntity.ok(savedTeacher);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Teacher with id " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete teacher", description = "Delete a teacher by their ID")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subject/{subject}")
    @Operation(summary = "Get teachers by subject", description = "Retrieve all teachers teaching a specific subject")
    public ResponseEntity<List<Teacher>> getTeachersBySubject(@PathVariable String subject) {
        List<Teacher> teachers = teacherService.getTeachersBySubject(subject);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/school/{schoolId}")
    @Operation(summary = "Get teachers by school", description = "Retrieve all teachers from a specific school")
    public ResponseEntity<List<Teacher>> getTeachersBySchool(@PathVariable String schoolId) {
        List<Teacher> teachers = teacherService.getTeachersBySchool(schoolId);
        return ResponseEntity.ok(teachers);
    }
} 