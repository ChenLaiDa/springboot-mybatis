package com.example.service;

import com.example.common.reponse.CommonPage;
import com.example.entity.Student;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    Student getStudent(Integer id);

    List<Student> getStudentList(Student student);

    List<Student> bathInsert();

    List<Student> bathUpdate();

    CommonPage<Student> selectPage(Integer pageNum,Integer pageSize);

    void exportStudentExcel(HttpServletResponse response, String fileName);
}
