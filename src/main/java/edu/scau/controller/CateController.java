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
	
	/**
	 * 获取分类树
	 * 
	 * [ { "parent":"一级分类名", "children":["二级分类1","二级分类2",...] }, ... ]
	 * 
	 * @param response
	 */
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
