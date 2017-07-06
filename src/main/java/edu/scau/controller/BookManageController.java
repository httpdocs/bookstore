package edu.scau.controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.scau.model.Book;
import edu.scau.service.BookService;
import edu.scau.util.JSONUtil;

@Controller
@RequestMapping("/bookmgr")
public class BookManageController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/add")
	public void add(Book book, String cate, HttpServletResponse response) {
		String json = bookService.add(book, cate);
		JSONObject j = new JSONObject(json);
		try {
			if (j.getInt("status") == 0) {
				response.sendRedirect("/admin/right_book_select.html");
			} else {
				response.getWriter().println(json);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/on")
	public void push(Book book, HttpServletResponse response) {
		String json = bookService.push(book, 1);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/off")
	public void off(Book book, HttpServletResponse response) {
		String json = bookService.push(book, 0);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/select")
	public void select(HttpServletResponse response) {
		try {
			response.getWriter().println(bookService.select());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/delete")
	public void delete(String isbn, HttpServletResponse response) {
		JSONObject json = bookService.delete(isbn);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/get")
	public void selectOne(String isbn, HttpServletResponse response){
		JSONObject json = bookService.get(isbn);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	@RequestMapping("/update")
	public void update(String param, HttpServletResponse response){
		System.out.println(param);
		JSONObject p = new JSONObject(param);
		Book book = new Book();
		book.setIsbn(p.getString("isbn"));
		book.setTitle(p.getString("title"));
		book.setPublish(p.getString("publish"));
		book.setIntroduction(p.getString("introduction"));
		book.setPrice(p.getBigDecimal("price"));
		JSONObject json = bookService.update(book);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
