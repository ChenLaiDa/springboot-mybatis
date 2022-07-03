package com.example.controller;

import com.example.common.reponse.BaseResult;
import com.example.entity.User;
import com.example.service.UserService;
import org.jsoup.Connection;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/3 12:17
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 添加用户
     */
    @PostMapping("/addUser")
    public BaseResult<?> addUser(@RequestBody  User user) {
        userService.addUser(user);
        return BaseResult.success();
    }
    /**
     * 更新用户
     */
    @PostMapping("/updateUser")
    public BaseResult<?> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return BaseResult.success();
    }


}
