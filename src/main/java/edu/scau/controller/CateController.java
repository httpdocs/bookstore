package edu.scau.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.scau.service.CateService;

@Controller
@RequestMapping("/cate")
public class CateController {

	@Autowired
	private CateService service;
	
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
