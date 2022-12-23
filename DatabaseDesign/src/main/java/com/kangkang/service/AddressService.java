package com.kangkang.service;

import com.kangkang.pojo.Address;

import java.util.List;

public interface AddressService {

    void add(Address address);

    List<Address> selectByCustomerId(int customerId);

    void update(Address address);

    void deleteById(int id);
}
