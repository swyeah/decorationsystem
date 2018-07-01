package com.sise.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutorizationInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("AutorizationInterceptor preHandle");
        boolean flag = false;
        String servletPath = httpServletRequest.getServletPath();


        String requestType = httpServletRequest.getHeader("x-requested-with");//识别ajax的响应头
        if (requestType != null && requestType.equals("x-requested-with")){
            System.out.println("ajax测试拦截器。。。。。" );

        }

        if (!flag){
            String name =(String)httpServletRequest.getSession().getAttribute("name");
            if (name == null||name.equals("")){
                System.out.println("拦截");
                httpServletRequest.setAttribute("message","请先登录再访问网站");
                httpServletRequest.getRequestDispatcher("/login/login").forward(httpServletRequest,httpServletResponse);
            }
            else{
                System.out.println("请放行");
                flag = true;
            }
        }
        return flag;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
