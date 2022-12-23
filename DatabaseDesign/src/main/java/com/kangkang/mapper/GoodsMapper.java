package com.kangkang.mapper;

import com.kangkang.pojo.Goods;
import com.kangkang.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {

    List<Goods> selectBrandByConditions(@Param("begin") int begin, @Param("size") int size, @Param("goods") Goods goods);

    int selectBrandByConditionsCount(@Param("goods") Goods goods);

    @Insert("insert into goods values (#{img},null,#{goodName},#{price},#{num},#{description},#{brand},#{statusStr},#{status},#{category})")
    void add(Goods goods);

    @Delete("delete from goods where id=#{id}")
    void deleteById(Goods goods);
    @Update("update goods set img=#{img},goodName=#{goodName},price=#{price},num=#{num},description=#{description},brand=#{brand},statusStr=#{statusStr},status=#{status},category=#{category} where id=#{id}")
    void update(Goods goods);
    @Select("select *from goods where id=#{id}")
    Goods selectById(int id);
}
