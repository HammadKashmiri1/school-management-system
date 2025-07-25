package com.example.school_database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Document(collection = "classes")
public class Class {

    @Id
    private String id;

    @NotBlank(message = "Class name is required")
    private String name;

    @Min(value = 1, message = "Grade must be at least 1")
    private int grade;

    private String schoolId;
    private String teacherId;

    // Constructors
    public Class() {}

    public Class(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }

    public String getSchoolId() { return schoolId; }
    public void setSchoolId(String schoolId) { this.schoolId = schoolId; }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }
} 