package com.pc.web.servlet;

import com.pc.domain.PageBean;
import com.pc.domain.User;
import com.pc.service.UserService;
import com.pc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示的条数

        //优化判断，如果没传参数过来，默认访问第一页
        if (currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)){
            rows = "5";
        }

        //获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();
        //遍历map将查询条件取出返回页面显示查询的条件
        Set<String> keySet = condition.keySet();
        for (String key : keySet){
            String value = condition.get(key)[0];
            request.setAttribute(key,value);
        }


        //2.调用service查询
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage,rows,condition);
//        System.out.println(pb);

        //3.将PageBean存入request
        request.setAttribute("pb",pb);

        //4.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
