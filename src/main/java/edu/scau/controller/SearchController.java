package edu.scau.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.scau.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService service;
	
	@RequestMapping("/cate")
	public void byCate(String cate, HttpServletResponse response){
		String json = service.byCate(cate);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
