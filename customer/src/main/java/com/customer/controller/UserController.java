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
import java.util.List;

@RestController
public class UserController {
    @Resource
    @Reference(version = "1.0.0")
    private UserService userService;

    //http://localhost:8089/getAll
    @GetMapping("getAll")
    private List<User> getAll(User user) {
        return userService.getAll(user);
    }

    //http://localhost:8089/getUser?id=1
    @GetMapping("getUser")
    private User getUser(User user) {
        return userService.getUser(user);
    }

    //http://localhost:8089/getUserById?id=3
    @GetMapping("getUserById")
    public User getUserById(Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("saveUser")
    public void saveUser() {
        User user = new User();
        user.setName("赵洋");
        user.setAge(12);
        user.setSex("女");
        int i = userService.saveUser(user);
    }

    @GetMapping("updateUser")
    public void updateUser() {
        User user = new User();
        user.setId(1);
        user.setName("赵洋");
        user.setAge(13);
        user.setSex("男");
        int i = userService.updateUser(user);
    }

    @GetMapping("deleteUser")
    public void deleteUser() {
        int id = 3;
        int i = userService.deleteUser(id);
    }
}