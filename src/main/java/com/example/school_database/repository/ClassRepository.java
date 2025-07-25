package com.example.school_database.repository;

import com.example.school_database.model.Class;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClassRepository extends MongoRepository<Class, String> {
    
    // Find classes by school
    List<Class> findBySchoolId(String schoolId);
    
    // Find classes by grade
    List<Class> findByGrade(int grade);
    
    // Find classes by teacher
    List<Class> findByTeacherId(String teacherId);
    
    // Find classes by school and grade
    List<Class> findBySchoolIdAndGrade(String schoolId, int grade);
} 