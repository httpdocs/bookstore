package edu.scau.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import edu.scau.model.Book;
import edu.scau.model.BookExample;

@Mapper
public interface BookMapper {

    @Select({"select ", " isbn, title, stock, price, discount, sold, author, publish, time, introduction, status, mainPic ", " from ", " book ", " where isbn=#{isbn}"})
    Book selectById(String isbn);

	@Update({"update book set status=#{status} where isbn=#{isbn}"})
	int status(Book book, int status);
	
	@Select({"select * from book"})
	List<Book> selectAll();
	
    int countByExample(BookExample example);

    int deleteByExample(BookExample example);

    @Delete({"delete from book where isbn=#{isbn}"})
    int deleteByPrimaryKey(String isbn);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExampleWithBLOBs(BookExample example);

    List<Book> selectByExample(BookExample example);

    Book selectByPrimaryKey(String isbn);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExampleWithBLOBs(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKeyWithBLOBs(Book record);

    int updateByPrimaryKey(Book record);
}