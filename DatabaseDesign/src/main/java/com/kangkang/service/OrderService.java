package com.kangkang.service;

import com.kangkang.pojo.Orders;
import com.kangkang.pojo.UserPageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {

    void buyGoods(Orders orders);

    UserPageBean<Orders> selectAll(int currentPage, int pageSize, Orders orders);

    String optionOfOrder(Orders orders);
}
