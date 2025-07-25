package com.example.school_database.controller;

import com.example.school_database.model.School;
import com.example.school_database.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@Tag(name = "School Management", description = "APIs for managing schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    @Operation(summary = "Get all schools", description = "Retrieve a list of all schools")
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> schools = schoolService.getAllSchools();
        return ResponseEntity.ok(schools);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get school by ID", description = "Retrieve a specific school by its ID")
    public ResponseEntity<School> getSchoolById(@PathVariable String id) {
        return schoolService.getSchoolById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "School with id " + id + " not found"));
    }

    @PostMapping
    @Operation(summary = "Create a new school", description = "Create a new school with the provided details")
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        School createdSchool = schoolService.saveSchool(school);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchool);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update school", description = "Update an existing school with the provided details")
    public ResponseEntity<School> updateSchool(@PathVariable String id, @RequestBody School updatedSchool) {
        return schoolService.getSchoolById(id)
                .map(existingSchool -> {
                    existingSchool.setName(updatedSchool.getName());
                    existingSchool.setAddress(updatedSchool.getAddress());
                    existingSchool.setPhone(updatedSchool.getPhone());
                    School savedSchool = schoolService.saveSchool(existingSchool);
                    return ResponseEntity.ok(savedSchool);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "School with id " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete school", description = "Delete a school by its ID")
    public ResponseEntity<Void> deleteSchool(@PathVariable String id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }
} 