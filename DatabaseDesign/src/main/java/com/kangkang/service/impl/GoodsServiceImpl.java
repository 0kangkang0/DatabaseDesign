package com.kangkang.service.impl;

import com.kangkang.mapper.GoodsMapper;
import com.kangkang.pojo.Goods;
import com.kangkang.pojo.UserPageBean;
import com.kangkang.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    public UserPageBean<Goods> selectByConditions(int currentPage, int pageSize, Goods goods){
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        // 处理brand条件，模糊表达式
        String goodName = goods.getGoodName();
        if (goodName != null && goodName.length() > 0) {
            goods.setGoodName("%" + goodName + "%");
        }
        String brand = goods.getBrand();
        if (brand != null && brand.length() > 0) {
            goods.setBrand("%" + brand + "%");
        }
        List<Goods> goods1 = goodsMapper.selectBrandByConditions(begin, pageSize, goods);
        int i = goodsMapper.selectBrandByConditionsCount(goods);
        UserPageBean<Goods> goodsUserPageBean = new UserPageBean<>();
        goodsUserPageBean.setTotalCount(i);
        goodsUserPageBean.setRows(goods1);
        return goodsUserPageBean;
    }

    @Override
    public void add(Goods goods) {
        goodsMapper.add(goods);
    }

    @Override
    public void deleteById(Goods goods) {
        goodsMapper.deleteById(goods);
    }

    @Override
    public void update(Goods goods) {
        goodsMapper.update(goods);
    }
}
