package com.example.controller;
import com.example.common.reponse.BaseResult;
import com.example.common.reponse.CommonPage;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.utils.DateUtil;
import com.example.utils.UploadUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @PostMapping("/addStudent")
    public BaseResult<Student> addStudent(@RequestBody Student student){
        if(student == null){
            return BaseResult.error("error","学生对象不能为空!");
        }
        studentService.addStudent(student);
        return BaseResult.success(student);
    }

    @GetMapping("/getStudent")
    public BaseResult<Student> getStudent(Integer id){
        if(id == null){
            return BaseResult.error("error","id不能为空!");
        }
        Student student = studentService.getStudent(id);
        return BaseResult.success(student);

    }

    @GetMapping("/getStudentList")
    public BaseResult<List<Student>> getStudent(Student student){
        if(student == null){
            return BaseResult.error("error","学生对象不能为空!");
        }
        List<Student> studentList = studentService.getStudentList(student);
        return BaseResult.success(studentList);
    }

    @GetMapping("/bathInsert")
    public BaseResult<List<Student>> bathInsert(){
        List<Student> studentList = studentService.bathInsert();
        return BaseResult.success(studentList);
    }

    @GetMapping("/bathUpdate")
    public BaseResult<List<Student>> bathUpdate(){
        List<Student> studentList = studentService.bathUpdate();
        return BaseResult.success(studentList);
    }

    @GetMapping("/selectPage")
    public BaseResult<CommonPage<Student>> selectPage(Integer pageNum,Integer pageSize){
        CommonPage<Student> studentCommonPage = studentService.selectPage(pageNum, pageSize);
        return BaseResult.success(studentCommonPage);
    }

    @GetMapping("/exportStudentExcel")
    public void exportToExcel(HttpServletResponse response, HttpServletRequest request,@RequestParam(value = "fileName") String fileName)  {
        String file = fileName + DateUtil.formatDateToStr(new Date(),"yyyyMMddHHmmss") + ".xlsx";
        studentService.exportStudentExcel(response,file);

    }

    @PostMapping("/uploadImage")
    public BaseResult uploadImage(MultipartFile file){
        String s = UploadUtil.uploadImg2Nginx(file);
        System.out.println(s);
        return BaseResult.success();

    }
}
