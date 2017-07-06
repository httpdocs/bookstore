package edu.scau.controller;

import edu.scau.model.Book;
import edu.scau.model.ViewObject;
import edu.scau.service.BooksService;
import edu.scau.service.PictureService;
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

    @RequestMapping(path = {"/book"})
    public String BookDetail(Model model,
                             @RequestParam("isbn") String isbn) {
        System.out.println(isbn);
        Book book = bookService.selectById(isbn);
        ViewObject vo = new ViewObject();
        vo.set("book", book);
        model.addAttribute("vo", vo);
        return "book_details";
    }



}
