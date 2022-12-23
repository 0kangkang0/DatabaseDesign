package com.kangkang.service.impl;

import com.kangkang.mapper.CustomerMapper;
import com.kangkang.mapper.UserMapper;
import com.kangkang.pojo.Customer;
import com.kangkang.pojo.User;
import com.kangkang.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public String add(Customer customer) {
        Customer customer1 = customerMapper.selectByName(customer.getName());
        User user = userMapper.selectByName(customer.getName());
        if(customer1!=null||user!=null){
            return "repeatUsername";
        }
        customerMapper.addCustomer(customer);
        return "register_success";
    }

    @Override
    public Customer login(String username, String password) {
        return customerMapper.selectByNameAndPassword(username,password);
    }
}
