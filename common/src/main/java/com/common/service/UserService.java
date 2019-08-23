package com.common.service;

import com.common.domain.User;

import java.util.List;

/**
 * @title: 用户接口
 * @description:
 * @author: shig
 * @create: 2019/8/21 15:39
 **/
public interface UserService {
    List<User> getAll(User user);

    User getUser(User user);

    User getUserById(int id);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
