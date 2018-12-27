package com.example.demo.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    //获取cookie
        Cookie[] cookies= request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("userPhone")) {
                    return true;
                }
            }
        }
        response.sendRedirect("/demo/myUser/getLogin");
        return false;
        }

    }
