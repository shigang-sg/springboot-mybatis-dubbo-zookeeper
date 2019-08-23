package com.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.domain.User;
import com.common.service.UserService;
import com.provider.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @title: 用户实现类
 * @description:
 * @author: shig
 * @create: 2019/8/21 15:48
 **/

@Service(version = "1.0.0")
public class UserserviceImpl implements UserService {
    private final Logger logger = Logger.getLogger(UserserviceImpl.class);

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    public UserMapper userMapper;

    public User getUser() {
        return userMapper.getUser(1);
    }

    /**
     * 获取user逻辑：
     * 如果缓存存在，从缓存中获取user信息
     * 如果缓存不存在，从 DB 中获取user信息，然后插入缓存
     */
    @Override
    public User getUserById(int id) {
        //从缓存中获取城市信息
        String key = "user_" + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();

        //缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        User u = operations.get(key);
        System.out.println("是否有缓存：" + hasKey + "  缓存中的值是：" + u);
        if (hasKey) {
            User user = operations.get(key);
            logger.info("UserImpl.updateUser() : 从缓存中获取了user >> " + user.toString());
            return user;
        }
        //从数据库中获取user数据
        User user = userMapper.getUserById(id);

        //插入缓存
        operations.set(key, user, 4, TimeUnit.HOURS);
        logger.info("UserImpl.findUserById() :user插入缓存 >> " + user.toString());
        return user;
    }

    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    /**
     * 更新user逻辑：
     * 如果缓存存在，从缓存中删除user信息
     * 如果缓存不存在，不操作
     */
    public int updateUser(User user) {
        int ret = userMapper.updateUser(user);

        //缓存存在，删除缓存
        String key = "user_" + user.getId();
        boolean haskey = redisTemplate.hasKey(key);
        if (haskey) {
            redisTemplate.delete(key);
            logger.info("UserImmpl.updateUser() : 从缓存中删除user >> " + user.toString());
        }
        return ret;
    }

    /**
     * 删除user逻辑：
     * 如果缓存存在，从缓存中删除user信息
     * 如果缓存不存在，不操作
     */
    public int deleteUser(int id) {
        int ret = userMapper.deleteUser(id);

        //缓存存在，删除缓存
        String key = "user_" + id;
        System.out.println("key的值为： " + key);
        boolean haskey = redisTemplate.hasKey(key);
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        User u = operations.get(key);
        System.out.println("是否有缓存：" + haskey + "  缓存中的值是：" + u);
        if (haskey) {
            logger.info("UserImmpl.updateUser() : 从缓存中删除user >> " + operations.get(key));
            redisTemplate.delete(key);
        }
        
        return ret;
    }
}
