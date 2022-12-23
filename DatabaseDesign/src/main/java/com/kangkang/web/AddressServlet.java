package com.kangkang.web;

import com.alibaba.fastjson.JSON;
import com.kangkang.pojo.Address;
import com.kangkang.service.AddressService;
import com.kangkang.util.GetCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/address/*")
public class AddressServlet extends BaseServlet{
    public void addNewAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Address address = JSON.parseObject(s, Address.class);
        HttpSession session = request.getSession();
        int id = (int)session.getAttribute("id");
        address.setCustomerId(id);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        AddressService bean = ctx.getBean(AddressService.class);
        bean.add(address);
        response.getWriter().write("success");
    }

    public void selectAllServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json;charset=utf-8");
        HttpSession session = request.getSession();
        int customerId = (int)session.getAttribute("id");
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        AddressService bean = ctx.getBean(AddressService.class);
        List<Address> address = bean.selectByCustomerId(customerId);
        for (Address a:address){
            a.setAllAddress(a.getProvinceCityArea()+a.getDetailAddress());
        }
        response.getWriter().write(JSON.toJSONString(address));
    }
    public void updateAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Address address = JSON.parseObject(s, Address.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        AddressService bean = ctx.getBean(AddressService.class);
        bean.update(address);
        response.getWriter().write("success");
    }
    public void deleteAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Address address = JSON.parseObject(s, Address.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        AddressService bean = ctx.getBean(AddressService.class);
        bean.deleteById(address.getId());
        response.getWriter().write("success");
    }
}
