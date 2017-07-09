package edu.scau.controller;

import edu.scau.model.Book;
import edu.scau.model.User;
import edu.scau.model.ViewObject;
import edu.scau.service.BooksService;
import edu.scau.service.PictureService;
import edu.scau.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Shoulder on 7/5/2017.
 */
@Controller
public class BookDetailController {
@Autowired
BooksService bookService;
@Autowired
PictureService pictureService;
@Autowired
UserService userService;

    @RequestMapping(path = {"/book"})
    public String BookDetail(Model model,HttpServletRequest request,
                             @RequestParam("isbn") String isbn) {
    	String id = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookiename = cookie.getName();
                String cookievalue = cookie.getValue();
                if ("ticket".equals(cookiename)) {
                    id = cookievalue;
                    ViewObject vo_user = new ViewObject();
                    User user = userService.getUser(id);
                    vo_user.set("user", user);
                    model.addAttribute("vo_user", vo_user);
                }
            }
        }
        
    	System.out.println(isbn);
        Book book = bookService.selectById(isbn);
        ViewObject vo = new ViewObject();
        vo.set("book", book);
        model.addAttribute("vo", vo);
        return "book_details";
    }



}
