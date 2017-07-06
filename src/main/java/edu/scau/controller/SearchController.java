package edu.scau.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.scau.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService service;
	
	/**
	 * 分类下的所有图书
	 * @param cate
	 * @param response
	 */
	@RequestMapping("/cate={cate}")
	public void byCate(@PathVariable("cate")String cate, HttpServletResponse response){
		String json = service.byCate(cate);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 按关键字搜索图书
	 * @param key
	 * @param response
	 */
	@RequestMapping("/key={key}")
	public void byKey(@PathVariable("key")String key, HttpServletResponse response){
		String json = service.byKey(key);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
