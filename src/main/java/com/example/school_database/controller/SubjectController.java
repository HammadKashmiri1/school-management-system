package com.example.school_database.controller;

import com.example.school_database.model.Subject;
import com.example.school_database.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@Tag(name = "Subject Management", description = "APIs for managing subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    @Operation(summary = "Get all subjects", description = "Retrieve a list of all subjects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get subject by ID", description = "Retrieve a specific subject by its ID")
    public ResponseEntity<Subject> getSubjectById(@PathVariable String id) {
        return subjectService.getSubjectById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Subject with id " + id + " not found"));
    }

    @PostMapping
    @Operation(summary = "Create a new subject", description = "Create a new subject with the provided details")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject createdSubject = subjectService.saveSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update subject", description = "Update an existing subject with the provided details")
    public ResponseEntity<Subject> updateSubject(
            @PathVariable String id,
            @RequestBody Subject updatedSubject) {
        return subjectService.getSubjectById(id)
                .map(existingSubject -> {
                    existingSubject.setName(updatedSubject.getName());
                    existingSubject.setDescription(updatedSubject.getDescription());
                    existingSubject.setSchoolId(updatedSubject.getSchoolId());
                    Subject savedSubject = subjectService.saveSubject(existingSubject);
                    return ResponseEntity.ok(savedSubject);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Subject with id " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete subject", description = "Delete a subject by its ID")
    public ResponseEntity<Void> deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/school/{schoolId}")
    @Operation(summary = "Get subjects by school", description = "Retrieve all subjects from a specific school")
    public ResponseEntity<List<Subject>> getSubjectsBySchool(@PathVariable String schoolId) {
        List<Subject> subjects = subjectService.getSubjectsBySchool(schoolId);
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get subjects by name", description = "Retrieve all subjects with a specific name")
    public ResponseEntity<List<Subject>> getSubjectsByName(@PathVariable String name) {
        List<Subject> subjects = subjectService.getSubjectsByName(name);
        return ResponseEntity.ok(subjects);
    }
} 