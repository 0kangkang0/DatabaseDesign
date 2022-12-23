package com.kangkang.service.impl;

import com.kangkang.mapper.UserMapper;
import com.kangkang.pojo.User;
import com.kangkang.pojo.UserPageBean;
import com.kangkang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAllUser();
    }


    @Override
    public User selectByUsernameAndPassword(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public UserPageBean<User> selectByConditions(int currentPage, int pageSize, User user) {
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        // 处理brand条件，模糊表达式
        String username = user.getUsername();
        if (username != null && username.length() > 0) {
            user.setUsername("%" + username + "%");
        }
        List<User> users = userMapper.selectByConditions(begin, pageSize, user);

        int i = userMapper.selectByConditionsCount(user);
        UserPageBean<User> userUserPageBean = new UserPageBean<>();
        userUserPageBean.setRows(users);
        userUserPageBean.setTotalCount(i);
        return userUserPageBean;
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteById(User user) {
        userMapper.deleteById(user);
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

}
