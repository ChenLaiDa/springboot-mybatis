package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/3 12:18
 */
public interface UserMapper {
    void addUser(@Param("user") User user);

    void updateUser(@Param("user") User user);
}
