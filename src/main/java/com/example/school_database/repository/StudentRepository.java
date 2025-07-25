package com.example.school_database.repository;

import com.example.school_database.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    
    // Find students by school
    List<Student> findBySchoolId(String schoolId);
    
    // Find students by class
    List<Student> findByClassId(String classId);
    
    // Case-insensitive search
    List<Student> findByFirstNameContainingIgnoreCase(String firstName);
    
    // Exact match
    List<Student> findByLastName(String lastName);
    
    // Combined search
    List<Student> findBySchoolIdAndFirstNameContaining(String schoolId, String firstName);
}