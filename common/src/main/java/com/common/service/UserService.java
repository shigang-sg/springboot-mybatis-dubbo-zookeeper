package com.common.service;

import com.common.domain.User;

/**
 * @title: 用户接口
 * @description:
 * @author: shig
 * @create: 2019/8/21 15:39
 **/
public interface UserService {
    User getUser();

    User getUserById(int id);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
