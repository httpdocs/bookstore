package edu.scau.interceptor;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.scau.controller.UserInformationController;

public class UserLoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest rq, HttpServletResponse req, Object arg2) throws Exception {
		System.out.println("执行到UserLoginInterceptor");
		
		
		String id = null;
        Cookie[] cookies = rq.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookiename = cookie.getName();
                String cookievalue = cookie.getValue();
                if ("ticket".equals(cookiename)) {
                    id = cookievalue;
                    UserInformationController.setUserid(id);
                    System.out.println("userid为："+UserInformationController.getUserid()+" id为："+id);  
                    return true;
                }
            }
        }
        if(id == null)
        	System.out.println("请先登录");
             rq.getRequestDispatcher("/home/loginUI").forward(rq, req);   
            return false;
	}

}
