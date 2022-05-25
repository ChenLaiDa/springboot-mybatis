package com.example.mapper;

import com.example.entity.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/10/3 19:16
 */
public interface ScoreMapper {
    List<Score> getScoreByStudentId(@Param("studentId") Integer StudentId);

}
