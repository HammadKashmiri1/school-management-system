package com.example.school_database.service;

import com.example.school_database.model.Subject;
import com.example.school_database.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(String id) {
        return subjectRepository.findById(id);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void deleteSubject(String id) {
        subjectRepository.deleteById(id);
    }

    public List<Subject> getSubjectsBySchool(String schoolId) {
        return subjectRepository.findBySchoolId(schoolId);
    }

    public List<Subject> getSubjectsByName(String name) {
        return subjectRepository.findByName(name);
    }
} 