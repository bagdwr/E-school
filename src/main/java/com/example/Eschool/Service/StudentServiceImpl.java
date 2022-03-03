package com.example.Eschool.Service;

import com.example.Eschool.Entity.Student;
import com.example.Eschool.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
       studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getById(id);
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @PostConstruct
    public void addValuesToDatabase(){
        List<Student>students= Arrays.asList(
                new Student(null,"Daurbek","Sakhtarov","87789115835",20),
                new Student(null,"Azamat","Umbetov","87777777777",20),
                new Student(null,"Abylai","Sagymbaev","87757757575",21),
                new Student(null,"Bakberger","Atymtaev","87747747474",19)
        );

        for (Student s:students){
            studentRepository.save(s);
        }
    }
}
