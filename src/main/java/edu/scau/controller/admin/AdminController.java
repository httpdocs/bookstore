package edu.scau.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.scau.model.Admin;
import edu.scau.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@RequestMapping("/add")
	public void add(Admin admin, HttpServletResponse response){
		String json = service.add(admin);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
