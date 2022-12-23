package com.kangkang.service;

import com.kangkang.pojo.Goods;
import com.kangkang.pojo.UserPageBean;

public interface GoodsService {
    UserPageBean<Goods> selectByConditions(int currentPage, int pageSize, Goods goods);
    void add(Goods goods);
    void deleteById(Goods goods);
    void update(Goods goods);
}
