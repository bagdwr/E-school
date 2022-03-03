package com.example.Eschool.Service;

import com.example.Eschool.Entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    int createStudent(Student student);
    int deleteStudentById(Long id);

    Student getStudentById(Long id);

    int saveStudent(Student student);
}
