package com.customer.controller;

/**
 * @title:
 * @description:
 * @author: shig
 * @create: 2019/8/21 17:35
 **/

import com.alibaba.dubbo.config.annotation.Reference;
import com.common.domain.User;
import com.common.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    @Reference(version = "1.0.0")
    private UserService userService;

    @GetMapping("getUser")
    public User user() {
        return userService.getUser();
    }

    @GetMapping("getUserById")
    public User getUserById() {
        int id = 3;
        return userService.getUserById(id);
    }

    @GetMapping("saveUser")
    public void saveUser() {
        User user = new User();
        user.setName("赵洋");
        user.setAge(12);
        user.setSex("女");
        int i = userService.saveUser(user);
        System.out.println("是否保存成功？-------------" + i);
    }

    @GetMapping("updateUser")
    public void updateUser() {
        User user = new User();
        user.setId(1);
        user.setName("赵洋");
        user.setAge(13);
        user.setSex("男");
        int i = userService.updateUser(user);
        System.out.println("是否保存成功？-------------" + i);
    }

    @GetMapping("deleteUser")
    public void deleteUser() {
        int id = 3;
        int i = userService.deleteUser(id);
        System.out.println("是否保存成功？-------------" + i);
    }
}