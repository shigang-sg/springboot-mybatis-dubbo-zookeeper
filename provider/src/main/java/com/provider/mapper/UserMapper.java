package com.provider.mapper;

import com.common.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: shig
 * @create: 2019/8/21 15:41
 **/
@Mapper
public interface UserMapper {
    //查询sql二种方式:1.注解方式  ，2.mapping文件
  /*  @Results(id = "userMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "age", property = "age"),
            @Result(column = "sex", property = "sex")})*/
//    @Select("SELECT * FROM u_user")
    List<User> getAll();

    //    @Select("SELECT * FROM u_user t WHERE t.id = #{id}")
//    @ResultMap("userMap")
    User getUser(Integer id);

    User getUserById(int id);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}