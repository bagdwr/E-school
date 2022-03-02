package com.example.Eschool.Service;

import com.example.Eschool.Entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student createStudent(Student student);
    void deleteStudentById(Long id);
}
