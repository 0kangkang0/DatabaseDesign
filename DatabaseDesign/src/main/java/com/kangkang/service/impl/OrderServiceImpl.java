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
            case "已发货": {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(cal.getTime());
                orders.setFhTime(format);
                ordersMapper.update(orders);
                return "发货成功";
            }
            case "待换货":
            case "待退款":
                ordersMapper.update(orders);
                return "申请成功";
            case "拒绝申请": {
                refuseAndCancel(orders);
                return "拒绝成功";
            }
            case "取消申请": {
                refuseAndCancel(orders);
                return "取消成功";
            }
            case "已完成": {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(cal.getTime());
                orders.setFinishTime(format);
                ordersMapper.update(orders);
                return "确认成功";
            }
            case "已换货":{
                ordersMapper.update(orders);
                orders.setStatus("待发货");
                orders.setFinishTime(null);
                orders.setFhTime(null);
                ordersMapper.add(orders);
                return "换货成功";
            }
            case "已退款":{
                ordersMapper.update(orders);
                int goodsId = orders.getGoodsId();
                Goods goods = goodsMapper.selectById(goodsId);
                goods.setNum(orders.getNum()+goods.getNum());
                return "退款成功";
            }
            case "删除订单":{
                ordersMapper.delete(orders);
                return "删除成功";
            }
        }
        return "false";
    }

    private void refuseAndCancel(Orders orders) {
        Orders orders1 = ordersMapper.selectById(orders);
        if (orders1.getFinishTime() != null) {
            orders1.setStatus("已完成");
        } else if (orders1.getFhTime() != null) {
            orders1.setStatus("已发货");
        } else if (orders1.getBuyTime() != null) {
            orders1.setStatus("待发货");
        }
        ordersMapper.update(orders1);
    }
}
