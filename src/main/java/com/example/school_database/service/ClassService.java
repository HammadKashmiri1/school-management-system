package com.example.school_database.service;

import com.example.school_database.model.Class;
import com.example.school_database.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Optional<Class> getClassById(String id) {
        return classRepository.findById(id);
    }

    public Class saveClass(Class clazz) {
        return classRepository.save(clazz);
    }

    public void deleteClass(String id) {
        classRepository.deleteById(id);
    }

    public List<Class> getClassesBySchool(String schoolId) {
        return classRepository.findBySchoolId(schoolId);
    }

    public List<Class> getClassesByGrade(int grade) {
        return classRepository.findByGrade(grade);
    }

    public List<Class> getClassesByTeacher(String teacherId) {
        return classRepository.findByTeacherId(teacherId);
    }
} 