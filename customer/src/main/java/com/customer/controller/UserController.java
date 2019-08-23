package com.customer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.common.domain.User;
import com.common.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title: 用户信息
 * @description:测试案例
 * @author: shig
 * @create: 2019/8/21 17:35
 **/
@Api(value = "用户controller", tags = {"用户操作接口"})
@RestController
public class UserController {

    /**
     * 获取所有用户信息
     * http://localhost:8089/getAll
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "获取所有用户信息", notes = "http://localhost:8089/getAll")
    //二种方式字段描述：1.写到实体类，2.下面描述
   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "string"),
            @ApiImplicitParam(name = "age", value = "年龄", dataType = "string"),
            @ApiImplicitParam(name = "sex", value = "性别", dataType = "int")
    })*/
    @GetMapping("getAll")
    private List<User> getAll(User user) {
        return userService.getAll(user);
    }

    @Resource
    @Reference(version = "1.0.0")
    private UserService userService;

    /**
     * 获取单个用户(根据id)
     * http://localhost:8089/getUser?id=1
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "获取单个用户(根据id)", notes = "http://localhost:8089/getUser?id=1")
    @GetMapping("getUser")
    private User getUser(User user) {
        return userService.getUser(user);
    }

    /**
     * 获取单个用户(根据id),写入到redis缓存
     * http://localhost:8089/getUserById?id=1
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取单个用户(根据id且写入到redis缓存中，下次自动redis缓存中取)", tags = {""}, notes = "http://localhost:8089/getUserById?id=1")
    @GetMapping("getUserById")
    public User getUserById(Integer id) {
        return userService.getUserById(id);
    }

    /**
     * 用户新增
     * http://localhost:8089/saveUser?id=2&name=波塞冬&age=27&sex=男
     *
     * @param user
     */
    @ApiOperation(value = "用户新增", notes = "http://localhost:8089/saveUser?id=2&name=波塞冬&age=27&sex=男")
    @GetMapping("saveUser")
    public void saveUser(User user) {
        userService.saveUser(user);
    }

    /**
     * 用户修改
     * http://localhost:8089/updateUser?id=2&name=波塞冬&age=27&sex=女
     *
     * @param user
     */
    @ApiOperation(value = "用户修改(根据id)", notes = "http://localhost:8089/updateUser?id=2&name=波塞冬&age=27&sex=女")
    @GetMapping("updateUser")
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    /**
     * 用户删除(根据id)
     * http://localhost:8089/deleteUser?id=2
     *
     * @param user
     */
    @ApiOperation(value = "删除用户(根据id)", notes = "http://localhost:8089/deleteUser?id=2")
    @GetMapping("deleteUser")
    public void deleteUser(User user) {
        userService.deleteUser(user.getId());
    }
}