package edu.scau.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.scau.model.Cate;
import edu.scau.service.admin.CateService;

@Controller
@RequestMapping("/catemgr")
public class CateController {

	@Autowired
	private CateService service;
	
	@RequestMapping("/create")
	public void create(Cate cate, HttpServletResponse response){
		String json = service.create(cate);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/list")
	public void list(HttpServletResponse response){
		String json = service.list();
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
