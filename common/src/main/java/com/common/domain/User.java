package com.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @title: 用户
 * @description:
 * @author: shig
 * @create: 2019/8/21 15:38
 **/
@ApiModel(value = "user对象", description = "用户对象user")
public class User implements Serializable {
    @ApiModelProperty(value = "主键", name = "id", dataType = "int")
    private int id;

    @ApiModelProperty(value = "用户名", name = "name", dataType = "String")
    private String name;

    @ApiModelProperty(value = "年龄", name = "age", dataType = "int")
    private int age;

    @ApiModelProperty(value = "性别", name = "age", dataType = "String")
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
