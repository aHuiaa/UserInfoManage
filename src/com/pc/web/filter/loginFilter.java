package com.pc.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */
@WebFilter("/*")
public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //0.强制转换http相关的
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //1. 获取资源请求路径
        String uri = request.getRequestURI();

        //2.判断是否包含登录相关资源路径
        if (uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/css/") || uri.contains("/fonts/") || uri.contains("/js/") || uri.contains("/checkCodeServlet")){
            //包含，用户就是想登录，放行
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            //不包含，需要验证用户是否登录
            //3.从session中获取user
            Object user = request.getSession().getAttribute("user");
            if (user != null){
                //不为空说明已经登录，放行
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                request.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
