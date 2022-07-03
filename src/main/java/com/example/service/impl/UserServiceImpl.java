package com.example.service.impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/3 12:19
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        user.setCreateTime(new Date());
        userMapper.addUser(user);

    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
