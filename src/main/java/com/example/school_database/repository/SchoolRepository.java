package com.example.school_database.repository;

import com.example.school_database.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends MongoRepository<School, String> {
    // Custom methods (if needed)
    // Example:
    // School findByName(String name);
}