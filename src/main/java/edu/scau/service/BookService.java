package edu.scau.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.scau.mapper.BookMapper;
import edu.scau.mapper.CateHasBookMapper;
import edu.scau.model.Book;
import edu.scau.util.DBSessionFactory;
import edu.scau.util.JSONUtil;

@Service
public class BookService {
	
	final private static String[] KEY = {"status", "msg"};
	final private static String[] MSG = {"操作成功", "未指定isbn", "字段不完整"};
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private CateHasBookMapper cateMapper;
	
	/**
	 * 添加图书
	 * @param book
	 * @return
	 */
	public String add(Book book, String cate){
		SqlSession session = DBSessionFactory.openSession();
		BookMapper mapper = session.getMapper(BookMapper.class);
		CateHasBookMapper cateMapper = session.getMapper(CateHasBookMapper.class);
		JSONObject json = new JSONObject();
		try{
			mapper.insert(book);
			cateMapper.insert(book.getIsbn(), cate);
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
	
	/**
	 * 所有图书
	 * @return
	 */
	public String select(){
		List<Book> list = bookMapper.selectAll();
		JSONArray array = JSONUtil.listToArray(list, Book.class);
		return array.toString();
	}
	
	/**
	 * 删除一本书
	 * @param isbn
	 * @return
	 */
	public JSONObject delete(String isbn){
		JSONObject json = new JSONObject();
		try{
			bookMapper.deleteByPrimaryKey(isbn);
			json.put("status", 0);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", -1);
			json.put("msg", "操作失败");
		}
		return json;
	}
	
	/**
	 * 获取一本书的信息
	 * @param isbn
	 * @return
	 */
	public JSONObject get(String isbn){
		Book book = bookMapper.selectByPrimaryKey(isbn);
		JSONObject json = JSONUtil.objectToObject(book, Book.class);
		return json;
	}
	
	public JSONObject update(Book book){
		JSONObject json = new JSONObject();
		try{
			bookMapper.updateByPrimaryKeySelective(book);
			json.put("status", 0);
			json.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", -1);
			json.put("msg", "操作失败");
		}
		return json;
	}
	
}
