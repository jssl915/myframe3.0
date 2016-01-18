package com.yf.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cykj.grcloud.util.Ognl;
import com.yf.web.Constant;

/**
 * 登录拦截器
 * 
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override  
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {  
    	if(Ognl.isEmpty(req.getSession().getAttribute(Constant.SESSION_USER))){
			res.sendRedirect(req.getContextPath() + "/login");
			return true; 
		}
    	return true; 
    }  
}
