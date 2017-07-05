package edu.scau.controller;

import edu.scau.model.Book;
import edu.scau.model.ViewObject;
import edu.scau.service.CartService;
import edu.scau.service.PictureService;
import edu.scau.service.BooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shoulder on 7/4/2017.
 */
@Controller
public class ShoppingController {
    @Autowired
    private CartService cartService;

    @Autowired
    BooksService booksService;

    @Autowired
    PictureService pictureService;

    private List<ViewObject> getBooksInCart(String id) {
        List<String> isbnList = cartService.getIsbns(id);
        List<ViewObject> vos = new ArrayList<>();
        for(String isbn : isbnList){
            Book book = booksService.selectById(isbn);
            ViewObject vo = new ViewObject();
            vo.set("book", book);
            vos.add(vo);
        }

        return vos;
    }
    @RequestMapping(path = {"/shopping"}, method = {RequestMethod.GET})
    public String ShoppingList(Model model, HttpServletRequest request) {

        String id = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookiename = cookie.getName();
                String cookievalue = cookie.getValue();
                if ("ticket".equals(cookiename)) {
                    id = cookievalue;
                    model.addAttribute("vos", getBooksInCart(id));
                    return "shopping";
                }
            }
        }
        if(id == null)
            model.addAttribute("msg", "请登录后再使用购物车");
            return "login";
    }
}


