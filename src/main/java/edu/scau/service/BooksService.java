package edu.scau.service;

import edu.scau.mapper.BookMapper;
import edu.scau.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shoulder on 7/4/2017.
 */
@Service
public class BooksService {
    @Autowired
    BookMapper bookMapper;

    public Book selectById(String id) {
        return bookMapper.selectById(id);
    }

    //    public List<Book> getBooksInCart(int userId, int offset, int limit) {
//        return bookMapper.getBooksInCart(userId, offset, limit);
//    }

}
