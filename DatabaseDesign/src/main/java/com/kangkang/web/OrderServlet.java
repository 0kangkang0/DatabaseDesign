package com.kangkang.web;

import com.alibaba.fastjson.JSON;
import com.kangkang.pojo.Address;
import com.kangkang.pojo.Customer;
import com.kangkang.pojo.Orders;
import com.kangkang.pojo.UserPageBean;
import com.kangkang.service.CustomerService;
import com.kangkang.service.OrderService;
import com.kangkang.util.GetCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet{
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        Orders orders = JSON.parseObject(params, Orders.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        OrderService bean = ctx.getBean(OrderService.class);
        bean.buyGoods(orders);
        response.getWriter().write("success");
    }
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        Orders orders = JSON.parseObject(params, Orders.class);
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if(role.equals("customer")){
            int id = (int)session.getAttribute("id");
            orders.setCustomerId(id);
        }
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        OrderService bean = ctx.getBean(OrderService.class);
        UserPageBean<Orders> ordersUserPageBean = bean.selectAll(currentPage, pageSize, orders);
        List<Orders> rows = ordersUserPageBean.getRows();
        for (Orders o:rows){
            o.setTotalPrice(o.getNum()*o.getPrice());
        }
        ordersUserPageBean.setRows(rows);
        String s = JSON.toJSONString(ordersUserPageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
    public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        Orders orders = JSON.parseObject(params, Orders.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        OrderService bean = ctx.getBean(OrderService.class);
        String s = bean.optionOfOrder(orders);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
}
