package com.example.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import com.example.common.reponse.CommonPage;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import com.example.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student addStudent(Student student) {
        student.setCreateTime(new Date());
        studentMapper.addStudent(student);
        Integer id = student.getId();
        log.info("添加成功，id为：{}", id);
        return student;
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public List<Student> getStudentList(Student student) {
        return studentMapper.getStudentList(student);
    }

    /**
     * 批量添加
     * @param
     * @return
     */
    @Override
    public List<Student> bathInsert() {
        Student student = new Student("大哥", 2);
        Student student1 = new Student("二哥", 1);
        ArrayList<Student> studentList = Lists.newArrayList(student, student1);
        studentMapper.bathInsert(studentList);
        return studentList;
    }

    /**
     * 批量更新
     *
     * @param
     */
    @Override
    public List<Student> bathUpdate() {
        Student student = new Student(20, "大哥", 12);
        Student student1 = new Student(21, "二哥", 34);
        ArrayList<Student> studentList = Lists.newArrayList(student, student1);
        studentMapper.bathUpdate(studentList);
        return studentList;
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public CommonPage<Student> selectPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentMapper.selectPage();
        PageInfo<Student> studentPageInfo = new PageInfo<>(students);
        CommonPage<Student> studentPage = new CommonPage<>();
        studentPage.setList(studentPageInfo.getList());
        studentPage.setPageNum(studentPageInfo.getPageNum());
        studentPage.setPageSize(studentPageInfo.getPageSize());
        studentPage.setTotal(studentPageInfo.getTotal());
        return studentPage;
    }

    /**
     * 导出excel
     */
    @Override
    public void exportStudentExcel(HttpServletResponse response, String fileName) {
        List<Student> studentList = studentMapper.getStudentList(new Student());
        exportExcel(response, studentList, fileName);
    }


    public static void exportExcel(HttpServletResponse response, List<Student> list, String fileName) {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        //样式
        CellStyle style = wb.createCellStyle();
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        //创建sheet
        Sheet sheet = wb.createSheet("学生统计");
        sheet.setColumnWidth(0, 25 * 256);
        sheet.setColumnWidth(1, 25 * 256);
        sheet.setColumnWidth(2, 25 * 256);
        sheet.setColumnWidth(3, 25 * 256);
        //首行
        Row headerRow = sheet.createRow(0);
        Cell cell = headerRow.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("编号");
        Cell cell1 = headerRow.createCell(1);
        cell1.setCellStyle(style);
        cell1.setCellValue("姓名");
        Cell cell2 = headerRow.createCell(2);
        cell2.setCellStyle(style);
        cell2.setCellValue("年龄");
        Cell cell3 = headerRow.createCell(3);
        cell3.setCellStyle(style);
        cell3.setCellValue("生日");
        //数据行
        int i = 1;
        for (Student student : list) {
            Row row = sheet.createRow(i);
            Cell cell4 = row.createCell(0);
            cell4.setCellStyle(style);
            cell4.setCellValue((student.getId() == null) ? "" : student.getId().toString());
            Cell cell5 = row.createCell(1);
            cell5.setCellStyle(style);
            cell5.setCellValue((student.getName() == null) ? "" : student.getName());
            Cell cell6 = row.createCell(2);
            cell6.setCellStyle(style);
            cell6.setCellValue((student.getAge() == null) ? "" : student.getAge().toString());
            Cell cell7 = row.createCell(3);
            cell7.setCellStyle(style);
            cell7.setCellValue((student.getBirthday() == null) ? "" : DateUtil.formatDateToStr(student.getBirthday(), "yyyy-MM-dd"));
            i++;
        }
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, DEFAULT_URL_ENCODING));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //删除本地文件
        wb.dispose();
    }

}



