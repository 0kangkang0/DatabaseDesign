package com.kangkang.mapper;

import com.kangkang.pojo.Customer;
import com.kangkang.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {
    @Insert("insert into customer values (null,#{name},#{password})")
    void addCustomer(Customer customer);
    @Select("select * from customer where id=#{id}")
    Customer selectCustomerById(int id);
    @Select("select *from customer where name=#{name}")
    Customer selectByName(String name);
    @Select("select * from customer where name=#{username} and password=#{password}")
    Customer selectByNameAndPassword(@Param("username") String username, @Param("password")String password);
}
