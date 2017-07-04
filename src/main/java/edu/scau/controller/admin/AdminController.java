package edu.scau.controller.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.scau.model.Admin;
import edu.scau.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@RequestMapping("/login")
	public ModelAndView login(Admin admin, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		Map<String, Object> map = service.login(admin);
		if((Integer)map.get("status") != 0){
			view.addObject("msg", map.get("msg"));
			view.setViewName("admin/login");
		} else {
			HttpSession session = request.getSession();//获取session对象
			session.setAttribute("admin", map.get("adminId"));//往session添加Attribute，保存用户信息
			session.setAttribute("name", map.get("name"));
			view.setViewName("admin/index");
		}
		return view;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();//销毁session
		return "admin/login";
	}
	
	@RequestMapping("/add")
	public void add(Admin admin, HttpServletResponse response){
		String json = service.add(admin);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delete")
	public void delete(Admin admin, HttpServletResponse response){
		String json = service.delete(admin);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
