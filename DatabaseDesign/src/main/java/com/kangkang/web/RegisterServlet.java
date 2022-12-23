package com.kangkang.web;

import com.alibaba.fastjson.JSON;
import com.kangkang.pojo.Customer;
import com.kangkang.service.CustomerService;
import com.kangkang.util.CheckCodeUtil;
import com.kangkang.util.GetCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Objects;

@WebServlet("/register/*")
public class RegisterServlet extends BaseServlet{
    public void getCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen",checkCode);
    }
    public void registerServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String checkCodeGen = (String)session.getAttribute("checkCodeGen");
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        Customer customer = JSON.parseObject(params, Customer.class);
        if(!checkCodeGen.equalsIgnoreCase(customer.getCheckCode())){
            response.getWriter().write("checkCodeErr");
            return;
        }
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        CustomerService bean = ctx.getBean(CustomerService.class);
        String add = bean.add(customer);
        response.getWriter().write(add);
    }
}
