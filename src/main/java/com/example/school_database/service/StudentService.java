package com.example.school_database.service;

import com.example.school_database.model.Student;
import com.example.school_database.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsBySchool(String schoolId) {
        return studentRepository.findBySchoolId(schoolId);
    }

    public List<Student> getStudentsByClass(String classId) {
        return studentRepository.findByClassId(classId);
    }
} 