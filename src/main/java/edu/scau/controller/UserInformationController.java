package edu.scau.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.scau.model.Address;
import edu.scau.service.UserInformationService;

@Controller
@RequestMapping("/UserIC")
public class UserInformationController {
	  @Autowired
      UserInformationService userInformationService;
	  
	  @RequestMapping("/add")
	  public String add(Model model,HttpServletRequest req,Address newAddress) throws Exception{
		    
		    
		     String userid= checkedLogin(req);
		     if(userid!=null){
		     model.addAttribute( userInformationService.add(newAddress,userid));
		     return "user_detail";
		     }
		     else{
		    	 System.out.println("请先登录");
		    	 return "login";
		     }
	  }
	  
	  @RequestMapping("/update")
	  public String update(Model model,HttpServletRequest req,Address newAddress) throws Exception{
		 
		     String userid= checkedLogin(req);
		     if(userid!=null){
		     model.addAttribute( userInformationService.update(newAddress,userid));
		     return "user_detail";
		     }
		     else{
		    	 System.out.println("请先登录");
		    	 return "login";
		     }
	  }
	  
	  private String checkedLogin(HttpServletRequest req) {
		  String id = null;
	        Cookie[] cookies = req.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                String cookiename = cookie.getName();
	                String cookievalue = cookie.getValue();
	                if ("ticket".equals(cookiename)) {
	                    id = cookievalue;
	                   
	                }
	            }
	        }  
	            return id;
	}
}
