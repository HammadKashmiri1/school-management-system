package com.example.school_database.repository;

import com.example.school_database.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String> {
    
    // Find subjects by school
    List<Subject> findBySchoolId(String schoolId);
    
    // Find subjects by name
    List<Subject> findByName(String name);
    
    // Case-insensitive name search
    List<Subject> findByNameContainingIgnoreCase(String name);
    
    // Find subjects by school and name
    List<Subject> findBySchoolIdAndName(String schoolId, String name);
} 