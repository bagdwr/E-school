package com.example.Eschool.Mapper;

import com.example.Eschool.Entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentBatisRepository {
    @Insert("INSERT INTO students values (null,#{age},#{firstname},#{phone},#{surname});")
    int insertStudent(Student student);

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student findById(Long id);

    @Select("select * from students")
    List<Student> findAll();

    @Update("update students set age=#{age}, firstname=#{firstname}, phone=#{phone}, surname=#{surname} where id=#{id};")
    int updateStudent(Student student);

    @Delete("delete from students where id=#{id}")
    int deleteById(Long id);
}
