package com.example.school_database.controller;

import com.example.school_database.model.Class;
import com.example.school_database.service.ClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@Tag(name = "Class Management", description = "APIs for managing classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    @Operation(summary = "Get all classes", description = "Retrieve a list of all classes")
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get class by ID", description = "Retrieve a specific class by its ID")
    public ResponseEntity<Class> getClassById(@PathVariable String id) {
        return classService.getClassById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Class with id " + id + " not found"));
    }

    @PostMapping
    @Operation(summary = "Create a new class", description = "Create a new class with the provided details")
    public ResponseEntity<Class> createClass(@RequestBody Class clazz) {
        Class createdClass = classService.saveClass(clazz);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClass);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update class", description = "Update an existing class with the provided details")
    public ResponseEntity<Class> updateClass(
            @PathVariable String id,
            @RequestBody Class updatedClass) {
        return classService.getClassById(id)
                .map(existingClass -> {
                    existingClass.setName(updatedClass.getName());
                    existingClass.setGrade(updatedClass.getGrade());
                    existingClass.setSchoolId(updatedClass.getSchoolId());
                    existingClass.setTeacherId(updatedClass.getTeacherId());
                    Class savedClass = classService.saveClass(existingClass);
                    return ResponseEntity.ok(savedClass);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Class with id " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete class", description = "Delete a class by its ID")
    public ResponseEntity<Void> deleteClass(@PathVariable String id) {
        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/school/{schoolId}")
    @Operation(summary = "Get classes by school", description = "Retrieve all classes from a specific school")
    public ResponseEntity<List<Class>> getClassesBySchool(@PathVariable String schoolId) {
        List<Class> classes = classService.getClassesBySchool(schoolId);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/grade/{grade}")
    @Operation(summary = "Get classes by grade", description = "Retrieve all classes of a specific grade")
    public ResponseEntity<List<Class>> getClassesByGrade(@PathVariable int grade) {
        List<Class> classes = classService.getClassesByGrade(grade);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/teacher/{teacherId}")
    @Operation(summary = "Get classes by teacher", description = "Retrieve all classes taught by a specific teacher")
    public ResponseEntity<List<Class>> getClassesByTeacher(@PathVariable String teacherId) {
        List<Class> classes = classService.getClassesByTeacher(teacherId);
        return ResponseEntity.ok(classes);
    }
} 