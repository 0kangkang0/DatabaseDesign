package com.kangkang.mapper;

import com.kangkang.pojo.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface OrdersMapper {
    @Insert("insert into orders values (null,#{num},#{status},#{buyTime},#{finishTime},#{fhTime},#{goodsId},#{addressId})")
    void add(Orders orders);

    List<Orders> selectAll(@Param("begin")int begin,@Param("size") int size,@Param("orders")Orders orders);

    int selectCount(@Param("begin")int begin,@Param("size") int size,@Param("orders")Orders orders);

    @Update("update orders set status=#{status},buyTime=#{buyTime},fhTime=#{fhTime},finishTime=#{finishTime} where id=#{id}")
    void update(Orders orders);
    @Select("select *from orders where id=#{id}")
    Orders selectById(Orders orders);
    @Delete("delete from orders where id=#{id}")
    void delete(Orders orders);
}
