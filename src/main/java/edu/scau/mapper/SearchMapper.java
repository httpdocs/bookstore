package edu.scau.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import edu.scau.mapper.view.SearchResult;

@Mapper
public interface SearchMapper {

	@Select({"select book.isbn isbn, title, price, discount, author, publish, mainPic from book, cate_has_book b where book.isbn=b.isbn and cate=#{cate}"})
	List<SearchResult> selectByCate(String cate);
	
	@Select({"select book.isbn isbn, title, price, discount, author, publish, mainPic from book, cate_has_book b where book.title like '%'||#{title}||'%'"})
	List<SearchResult> selectByKey(String title);
	
}
