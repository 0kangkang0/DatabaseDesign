package com.kangkang.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        String[] urls ={"/login.html","/css/","/loginServlet","img","js","register"};
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        String requestURI = request1.getRequestURI();
        for (String url:urls){
            if(requestURI.contains(url)){
                chain.doFilter(request,response);
                return;
            }
        }
        HttpSession session = request1.getSession();
        String username = (String)session.getAttribute("username");

        if(username==null){
            response1.sendRedirect("http://localhost:8080/DatabaseDesign/login.html");
            return;
        }

        String _role = (String)session.getAttribute("role");
        Cookie cookie1 = new Cookie("role", URLEncoder.encode(_role));
        response1.addCookie(cookie1);
        Cookie[] cookies = request1.getCookies();
        String role = null;
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("role")){
                role=URLDecoder.decode(cookie.getValue());
                break;
            }
        }
        if(role==null){
            role=_role;
        }
        assert role != null;
        if(role.equals("customer")&&requestURI.endsWith("mainPage.html")){
            response1.sendRedirect("http://localhost:8080/DatabaseDesign/login.html");
        }else if(!role.equals("customer")&&requestURI.endsWith("customer.html")){
            response1.sendRedirect("http://localhost:8080/DatabaseDesign/login.html");
        }else
            chain.doFilter(request1,response1);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
