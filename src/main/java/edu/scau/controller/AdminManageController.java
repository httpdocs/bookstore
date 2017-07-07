package edu.scau.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.scau.model.Admin;
import edu.scau.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminManageController {

	@Autowired
	private AdminService service;

	@RequestMapping("/login")
	public ModelAndView login(Admin admin, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		Map<String, Object> map = service.login(admin);
		if ((Integer) map.get("status") != 0) {
			view.addObject("msg", map.get("msg"));
			view.setViewName("admin/login");
		} else {
			HttpSession session = request.getSession();// 鑾峰彇session瀵硅薄
			session.setAttribute("admin", map.get("adminId"));// 寰�session娣诲姞Attribute锛屼繚瀛樼敤鎴蜂俊鎭�
			session.setAttribute("name", map.get("name"));
			view.setViewName("admin/index");
		}
		return view;
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();// 閿�姣乻ession
		return "admin/login";
	}

	@RequestMapping("get")
	public void get(String adminid, HttpServletResponse response) {
		String json = service.get(adminid);
		try {
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/add")
	public void add(Admin admin, HttpServletResponse response) {
		System.err.println("Con:  " + admin.getAdminid() + "name" + admin.getName() + "pwd" + admin.getPassword()
				+ "au:" + admin.getAuthority());
		String json = service.add(admin);
		JSONObject jsonObject = new JSONObject(json);
		try {
			if (jsonObject.getInt("status") == 0) {
				response.sendRedirect("/admin/manage_first.html");
			} else {
				response.getWriter().println(json);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/delete")
	public void delete(Admin admin, HttpServletResponse response) {
		String json = service.delete(admin);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/list")
	public void list(HttpServletResponse response) {
		try {
			response.getWriter().println(service.list());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/update")
	public void update(String adminid, String name, String password, HttpServletResponse response) {
		if (adminid == null || name == null || password == null) {
			try {
				response.getWriter().println("参数缺少");
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		Admin admin = new Admin();
		admin.setAdminid(adminid);
		admin.setPassword(password);
		admin.setName(name);
		JSONObject json = service.update(admin);
		try {
			if (json.getInt("status") == 0) {
				response.sendRedirect("/admin/manage_first.html");
			} else {
				response.getWriter().println(json.getString("msg"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
