package com.kangkang.service;

import com.kangkang.pojo.Customer;

public interface CustomerService {
    String add(Customer customer);
    Customer login(String username,String password);
}
