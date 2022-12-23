package com.kangkang.service;

import com.kangkang.pojo.User;
import com.kangkang.pojo.UserPageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    List<User> selectAll();

    User selectByUsernameAndPassword(String username,String password);

    UserPageBean<User> selectByConditions(int begin, int size, User user);

    void update(User user);

    void deleteById(User user);

    void add(User user);
}
