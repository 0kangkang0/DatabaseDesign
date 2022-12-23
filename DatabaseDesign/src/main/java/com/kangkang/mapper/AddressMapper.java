package com.kangkang.mapper;

import com.kangkang.pojo.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {
    @Insert("insert into address values (null,#{detailAddress},#{telephone},#{name},#{customerId},#{province},#{city},#{area},#{provinceCityArea})")
    void add(Address address);
    @Select("select *from address where customerId=#{customerId}")
    List<Address> selectByCustomerId(int customerId);
    @Update("update address set detailAddress=#{detailAddress},telephone=#{telephone},name=#{name},province=#{province},city=#{city},area=#{area},provinceCityArea=#{provinceCityArea} where id=#{id}")
    void update(Address address);
    @Delete("delete from address where id=#{id}")
    void deleteById(int id);
}
