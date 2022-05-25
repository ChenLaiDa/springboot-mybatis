package com.example.mapper;

import com.example.entity.Student;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;


import java.util.List;
public interface StudentMapper {
    void addStudent(@Param("student") Student student);

    Student getStudent(@Param("id") Integer id);

    List<Student> getStudentList(@Param("student")Student student);

    void bathInsert(@Param("studentList") List<Student> studentList);

    void bathUpdate(@Param("updateStudentList") List<Student> studentList);

    List<Student> selectPage();



}
