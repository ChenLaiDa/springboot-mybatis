package com.example;

import com.example.common.reponse.CommonPage;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @Author: ChenC
 * @Date: 2021/7/23 14:51
 */
@SpringBootTest
public class test {
    @Autowired
    private StudentService studentService;
    @Test
    public void getLastYear(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        //过去一年
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        Date y = calendar.getTime();
        System.out.println(y);
        String year = format.format(y);
        System.out.println("过去一年："+year);
    }

    @Test
    public void addStudent(){
        Student student = new Student();
        student.setName("chenchong");
        student.setAge(23);
        studentService.addStudent(student);
        Integer id = student.getId();
        System.out.println("iddddddd="+id);

    }

    @Test
    public void selectPage(){
        CommonPage<Student> studentCommonPage = studentService.selectPage(1, 2);
        System.out.println(studentCommonPage);

    }
}
