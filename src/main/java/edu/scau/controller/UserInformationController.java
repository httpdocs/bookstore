package edu.scau.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;

import edu.scau.model.Address;
import edu.scau.service.UserInformationService;

@Controller
public class UserInformationController {
	  @Autowired
      UserInformationService userInformationService;
	  
	  private String userid=null;
	  
	  @RequestMapping("/UICadd")
	  public String UICadd(Model model,HttpServletRequest rq,HttpServletResponse req,Address newAddress) throws Exception{
		    
		     System.out.println("UICadd执行到这里了："+newAddress.getDetail()+"userid为"+userid);
		     
		     JSONObject jsonObject=new JSONObject();
		     jsonObject.put("UICaddress", userInformationService.add(newAddress,userid));
		     System.out.println(jsonObject);
		     model.addAttribute("UICaddress", userInformationService.add(newAddress,userid));
		     return "user_detail";
		    
	  }
	  
	  
	  @RequestMapping(path = {"/UICchecked"}, method = {RequestMethod.GET})
	    public String checkedLogin(HttpServletRequest request) {

	        String id = null;
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                String cookiename = cookie.getName();
	                String cookievalue = cookie.getValue();
	                if ("ticket".equals(cookiename)) {
	                    id = cookievalue;
	                    userid=id;
	                    System.out.println("userid为："+userid+" id为："+id);
	                    return "user_detail";
	                }
	            }
	        }
	        if(id == null)
	        	System.out.println("请先登录");
	            return "login";
	    }
	  
	  
	  @RequestMapping("/test")
	  public String test(Model model,HttpServletRequest request,Address newAddress){
		  System.out.println("执行到这里了："+newAddress.getDetail());
		  return "user_detail";
	  }
	  
	
	  
	 
}

