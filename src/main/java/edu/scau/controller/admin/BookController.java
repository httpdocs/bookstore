package edu.scau.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.scau.model.Book;
import edu.scau.service.admin.BookService;

@Controller
@RequestMapping("/bookmgr")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/add")
	public void add(Book book, HttpServletResponse response){
		String json = bookService.add(book);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/on")
	public void push(Book book, HttpServletResponse response){
		String json = bookService.push(book, 1);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/off")
	public void off(Book book, HttpServletResponse response){
		String json = bookService.push(book, 0);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
