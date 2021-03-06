package edu.scau.controller;

import java.io.IOException;

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
import edu.scau.util.JSONUtil;

@Controller
public class UserInformationController {
	  @Autowired
      UserInformationService userInformationService;
	  
	  JSONObject json;
	  private static String userid=null;
	  
	  @RequestMapping("/UICadd")
	  public String UICadd(Model model,HttpServletRequest rq,HttpServletResponse req,Address newAddress) throws Exception{
		    
		     System.out.println("UICadd执行到这里了："+newAddress.getDetail()+"userid为"+userid);
		     
		     json=changeToJson(userInformationService.add(newAddress,userid));
		     if(json!=null){
		    	 model.addAttribute("UICresult", "修改成功");
		     }else{
		    	 model.addAttribute("UICresult", "修改失败");
		     }
//		     model.addAttribute("UICaddress", userInformationService.add(newAddress,userid));
		     return "user_detail";
		    
	  }
	  
	  
	  @RequestMapping(path = {"/UICini"}, method = {RequestMethod.GET})
	    public String ini(HttpServletRequest request) {
		  
            	 json=changeToJson(userInformationService.getAddress(userid));
                 return "user_detail";
       
	    }
	  
	  
	  @RequestMapping("/getJson")
	  public void getJson(HttpServletResponse response){
		  System.out.println("getJson返回的数据"+json.toString());
		  try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  
	  
	  private JSONObject changeToJson(Address address){
		  JSONObject myJsonObject=null;
		  if(address!=null){
			  myJsonObject = JSONUtil.objectToObject(address,Address.class);
			  
		  }
		  return myJsonObject;
	  }
	  
	 public static void setUserid(String newUserid){
		 userid=newUserid;
	 }
	 
	 public static String getUserid(){
		 return userid;
	 }
}

