package com.example.entity;

import com.example.common.reponse.BaseEntity;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/10/3 19:10
 */
public class Course extends BaseEntity {
    private Integer id;
    private String courseName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("id=").append(id);
        sb.append(", courseName='").append(courseName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
