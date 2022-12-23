package com.kangkang.service.impl;

import com.kangkang.mapper.AddressMapper;
import com.kangkang.pojo.Address;
import com.kangkang.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;
    public void add(Address address){
        addressMapper.add(address);
    }

    @Override
    public List<Address> selectByCustomerId(int customerId) {
        return addressMapper.selectByCustomerId(customerId);
    }

    @Override
    public void update(Address address) {
        addressMapper.update(address);
    }

    @Override
    public void deleteById(int id) {
        addressMapper.deleteById(id);
    }
}
