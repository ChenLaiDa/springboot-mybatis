package com.example.controller;
import com.example.common.reponse.BaseResult;
import com.example.common.reponse.CommonPage;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.utils.DateUtil;
import com.example.utils.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "学生管理")
public class StudentController {
    @Resource
    private StudentService studentService;

    @ApiOperation(value = "添加学生", notes = "添加学生")
    @PostMapping("/addStudent")
    public BaseResult<Student> addStudent(@RequestBody Student student){
        if(student == null){
            return BaseResult.error("error","学生对象不能为空!");
        }
        studentService.addStudent(student);
        return BaseResult.success(student);
    }

    @ApiOperation(value="根据id查询学生", notes="根据id查询学生")
    @GetMapping("/getById")
    public BaseResult<Student> getById(Integer id){
        if(id == null){
            return BaseResult.error("error","id不能为空!");
        }
        Student student = studentService.getById(id);
        return BaseResult.success(student);

    }

    @GetMapping("/getStudentList")
    @ApiOperation(value = "查询学生列表", notes = "查询学生列表")
    public BaseResult<List<Student>> getStudent(Student student){
        if(student == null){
            return BaseResult.error("error","学生对象不能为空!");
        }
        List<Student> studentList = studentService.getStudentList(student);
        return BaseResult.success(studentList);
    }

    @ApiOperation(value = "批量添加学生", notes = "批量添加学生")
    @GetMapping("/bathInsert")
    public BaseResult<List<Student>> bathInsert(){
        List<Student> studentList = studentService.bathInsert();
        return BaseResult.success(studentList);
    }

    @ApiOperation(value = "批量更新学生", notes = "批量更新学生")
    @GetMapping("/bathUpdate")
    public BaseResult<List<Student>> bathUpdate(){
        List<Student> studentList = studentService.bathUpdate();
        return BaseResult.success(studentList);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/selectPage")
    public BaseResult<CommonPage<Student>> selectPage(Integer pageNum,Integer pageSize){
        CommonPage<Student> studentCommonPage = studentService.selectPage(pageNum, pageSize);
        return BaseResult.success(studentCommonPage);
    }

    @ApiOperation(value = "导出excel", notes = "导出excel")
    @GetMapping("/exportStudentExcel")
    public void exportToExcel(HttpServletResponse response, HttpServletRequest request,@RequestParam(value = "fileName") String fileName)  {
        String file = fileName + DateUtil.formatDateToStr(new Date(),"yyyyMMddHHmmss") + ".xlsx";
        studentService.exportStudentExcel(response,file);

    }

    @ApiOperation(value = "上传图片到服务器", notes = "上传图片到服务器")
    @PostMapping("/uploadImage")
    public BaseResult uploadImage(MultipartFile file){
        String s = UploadUtil.uploadImg2Nginx(file);
        System.out.println(s);
        return BaseResult.success();

    }
}
