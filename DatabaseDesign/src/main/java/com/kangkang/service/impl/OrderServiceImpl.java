package com.kangkang.service.impl;

import com.kangkang.mapper.GoodsMapper;
import com.kangkang.mapper.OrdersMapper;
import com.kangkang.pojo.Goods;
import com.kangkang.pojo.Orders;
import com.kangkang.pojo.UserPageBean;
import com.kangkang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public void buyGoods(Orders orders) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(cal.getTime());
        orders.setBuyTime(format);
        Goods goods = goodsMapper.selectById(orders.getGoodsId());
        goods.setNum(goods.getNum() - orders.getNum());
        goodsMapper.update(goods);
        ordersMapper.add(orders);
    }

    @Override
    public UserPageBean<Orders> selectAll(int currentPage, int pageSize, Orders orders) {
        int begin = (currentPage - 1) * pageSize;
        if (orders.getCustomerName() != null && orders.getCustomerName().length() > 0) {
            orders.setCustomerName("%" + orders.getCustomerName() + "%");
        }
        if (orders.getGoodsName() != null && orders.getGoodsName().length() > 0) {
            orders.setGoodsName("%" + orders.getGoodsName() + "%");
        }
        if (orders.getStatus() != null && orders.getStatus().length() > 0) {
            orders.setStatus("%" + orders.getStatus() + "%");
        }
        UserPageBean<Orders> ordersUserPageBean = new UserPageBean<>();
        List<Orders> orders1 = ordersMapper.selectAll(begin, pageSize, orders);
        ordersUserPageBean.setRows(orders1);
        int i = ordersMapper.selectCount(begin, pageSize, orders);
        ordersUserPageBean.setTotalCount(i);
        return ordersUserPageBean;
    }

    @Override
    public String optionOfOrder(Orders orders) {
        switch (orders.getStatus()) {
            case "?????????": {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(cal.getTime());
                orders.setFhTime(format);
                ordersMapper.update(orders);
                return "????????????";
            }
            case "?????????":
            case "?????????":
                ordersMapper.update(orders);
                return "????????????";
            case "????????????": {
                refuseAndCancel(orders);
                return "????????????";
            }
            case "????????????": {
                refuseAndCancel(orders);
                return "????????????";
            }
            case "?????????": {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(cal.getTime());
                orders.setFinishTime(format);
                ordersMapper.update(orders);
                return "????????????";
            }
            case "?????????":{
                ordersMapper.update(orders);
                orders.setStatus("?????????");
                orders.setFinishTime(null);
                orders.setFhTime(null);
                ordersMapper.add(orders);
                return "????????????";
            }
            case "?????????":{
                ordersMapper.update(orders);
                int goodsId = orders.getGoodsId();
                Goods goods = goodsMapper.selectById(goodsId);
                goods.setNum(orders.getNum()+goods.getNum());
                return "????????????";
            }
            case "????????????":{
                ordersMapper.delete(orders);
                return "????????????";
            }
        }
        return "false";
    }

    private void refuseAndCancel(Orders orders) {
        Orders orders1 = ordersMapper.selectById(orders);
        if (orders1.getFinishTime() != null) {
            orders1.setStatus("?????????");
        } else if (orders1.getFhTime() != null) {
            orders1.setStatus("?????????");
        } else if (orders1.getBuyTime() != null) {
            orders1.setStatus("?????????");
        }
        ordersMapper.update(orders1);
    }
}
