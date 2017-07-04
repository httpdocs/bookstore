package edu.scau.service.admin;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.scau.mapper.BookMapper;
import edu.scau.model.Book;
import edu.scau.util.DBSessionFactory;

@Service
public class BookService {
	
	final private static String[] KEY = {"status", "msg"};
	final private static String[] MSG = {"操作成功", "未指定isbn", "字段不完整"};
	
	@Autowired
	private BookMapper bookMapper;
	
	/**
	 * 添加图书
	 * @param book
	 * @return
	 */
	public String add(Book book){
		SqlSession session = DBSessionFactory.openSession();
		BookMapper mapper = session.getMapper(BookMapper.class);
		JSONObject json = new JSONObject();
		try{
			mapper.insert(book);
			session.commit();
			json.put(KEY[0], 0);
			json.put(KEY[1], MSG[0]);
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.put(KEY[0], 0);
			json.put(KEY[1], MSG[2]);
			return json.toString();
		}
	}
	
	/**
	 * 图书上/下架
	 * @param book
	 * @return
	 */
	public String push(Book book, int status){
		JSONObject json = new JSONObject();
		if(book.getIsbn() == null || book.getIsbn().equals("")){
			json.put(KEY[0], 1);
			json.put(KEY[1], MSG[1]);
		} else {
			bookMapper.status(book, status);
			json.put(KEY[0], 0);
			json.put(KEY[1], MSG[0]);
		}
		return json.toString();
	}
	
	
	
}
