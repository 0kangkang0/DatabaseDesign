package com.kangkang.web;

import com.alibaba.fastjson.JSON;
import com.kangkang.pojo.Goods;
import com.kangkang.pojo.UserPageBean;
import com.kangkang.service.GoodsService;
import com.kangkang.util.GetCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/goods/*")
public class GoodsServlet extends BaseServlet{
    public void selectAllServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        Goods goods = JSON.parseObject(params, Goods.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        GoodsService bean = ctx.getBean(GoodsService.class);
        UserPageBean<Goods> goodsUserPageBean = bean.selectByConditions(currentPage, pageSize, goods);
        String s = JSON.toJSONString(goodsUserPageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
    public void updateGoodsServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Goods goods = JSON.parseObject(s, Goods.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        GoodsService bean = ctx.getBean(GoodsService.class);
        bean.update(goods);
        response.getWriter().write("success");
    }
    public void deleteGoodsByIdServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Goods goods = JSON.parseObject(s, Goods.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        GoodsService bean = ctx.getBean(GoodsService.class);
        bean.deleteById(goods);
        response.getWriter().write("success");
    }
    public void addGoodsServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Goods goods = JSON.parseObject(s, Goods.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        GoodsService bean = ctx.getBean(GoodsService.class);
        bean.add(goods);
        response.getWriter().write("success");
    }
}
