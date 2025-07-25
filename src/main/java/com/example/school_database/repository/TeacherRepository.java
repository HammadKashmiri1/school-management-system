package com.example.school_database.repository;

import com.example.school_database.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {
    
    // Find teachers by subject
    List<Teacher> findBySubject(String subject);
    
    // Find teachers by school
    List<Teacher> findBySchoolId(String schoolId);
    
    // Case-insensitive subject search
    List<Teacher> findBySubjectContainingIgnoreCase(String subjectPart);
    
    // Find teachers by both school and subject
    List<Teacher> findBySchoolIdAndSubject(String schoolId, String subject);
}