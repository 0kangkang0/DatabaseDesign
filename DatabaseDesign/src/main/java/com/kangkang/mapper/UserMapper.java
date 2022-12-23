package com.kangkang.mapper;

import com.kangkang.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    //查询所有用户账号信息
    @Select("select * from user")
    List<User> selectAllUser();
    //以用户名和密码查询
    @Select("select * from user where username=#{username} and password=#{password}")
    User selectByUsernameAndPassword(@Param("username")String username,@Param("password")String password);

    List<User> selectByConditions(@Param("begin")int begin, @Param("size")int size, @Param("user")User user);

    int selectByConditionsCount(@Param("user")User user);
    @Update("update user set username =#{username},password=#{password},email=#{email},telephone=#{telephone},role=#{role},status=#{status} where id=#{id};")
    void update(User user);
    @Delete("delete from user where id=#{id}")
    void deleteById(User user);
    @Insert("insert into user values (null,#{username},#{email},#{telephone},#{status},#{password},#{role})")
    void add(User user);
    @Select("select *from user where username=#{name}")
    User selectByName(String name);
}
