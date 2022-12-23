package com.kangkang.web;

import com.alibaba.fastjson.JSON;
import com.kangkang.pojo.Customer;
import com.kangkang.pojo.User;
import com.kangkang.pojo.UserPageBean;
import com.kangkang.service.CustomerService;
import com.kangkang.service.UserService;
import com.kangkang.util.GetCtx;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    public void loginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();

        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        User user1 = JSON.parseObject(s, User.class);
        String username = user1.getUsername();
        String password = user1.getPassword();

        UserService userService = ctx.getBean(UserService.class);
        User user = userService.selectByUsernameAndPassword(username, password);
        CustomerService customerService = ctx.getBean(CustomerService.class);
        Customer customer = customerService.login(username, password);
        if (user != null && user.getStatus() == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("id",user.getId());
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            session.setAttribute("role",user.getRole());
            Cookie cookie = new Cookie("role", URLEncoder.encode(user.getRole()));
            response.addCookie(cookie);
            response.getWriter().write("manager");
        }else if(customer!=null){
            HttpSession session = request.getSession();
            session.setAttribute("id",customer.getId());
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            session.setAttribute("role","customer");
            Cookie cookie = new Cookie("role", "customer");
            response.addCookie(cookie);
            response.getWriter().write("customer");
        }else {
            response.getWriter().write("login_false");
        }
    }

    public void selectAllServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        User user = JSON.parseObject(params, User.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        UserService bean = ctx.getBean(UserService.class);
        UserPageBean<User> userPageBean = bean.selectByConditions(currentPage, pageSize, user);
        for (User u: userPageBean.getRows()){
            u.setPassword(null);
        }
        String s = JSON.toJSONString(userPageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
    public void uploadImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String UPLOAD_DIRECTORY = "upload";
        final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
        final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
        final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        String uploadPath = "src\\main\\resources\\" + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + URLEncoder.encode(fileName);
                        File storeFile = new File(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        response.getWriter().write("http://localhost:8080/DatabaseDesign/resources/upload/"+storeFile.getName());
                    }
                }
            }
        } catch (Exception ex) {
            response.getWriter().write("false");
        }
    }
    public void updateUserManagerServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        User user = JSON.parseObject(s, User.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        UserService bean = ctx.getBean(UserService.class);
        bean.update(user);
        response.getWriter().write("success");
    }
    public void deleteUserManagerByIdServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        User user = JSON.parseObject(s, User.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        UserService bean = ctx.getBean(UserService.class);
        bean.deleteById(user);
        response.getWriter().write("success");
    }
    public void addNewUserManagerServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        User user = JSON.parseObject(s, User.class);
        AnnotationConfigApplicationContext ctx = GetCtx.getCtx();
        UserService bean = ctx.getBean(UserService.class);
        bean.add(user);
        response.getWriter().write("success");
    }
}
