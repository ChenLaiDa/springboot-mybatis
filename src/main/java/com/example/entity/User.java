package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.checkerframework.checker.index.qual.Positive;

import java.util.Date;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/3 12:15
 */
@Data
public class User {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
}
