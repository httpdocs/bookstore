package edu.scau.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.scau.model.CateHasBookExample;
import edu.scau.model.CateHasBookKey;

@Mapper
public interface CateHasBookMapper {
	
    int countByExample(CateHasBookExample example);

    int deleteByExample(CateHasBookExample example);

    int deleteByPrimaryKey(CateHasBookKey key);

    @Select({"select * from cate_has_book where isbn=#{isbn} and cate=#{cate}"})
    CateHasBookKey select(String isbn, String cate);
    
    @Insert({"insert into cate_has_book values(#{cate}, #{isbn})"})
    int insert(@Param("isbn")String isbn, @Param("cate")String cate);
    
    @Delete({"delete from cate_has_book where isbn=#{isbn} and cate=#{cate}"})
    int delete(String isbn, String cate);

    int insertSelective(CateHasBookKey record);

    List<CateHasBookKey> selectByExample(CateHasBookExample example);

    int updateByExampleSelective(@Param("record") CateHasBookKey record, @Param("example") CateHasBookExample example);

    int updateByExample(@Param("record") CateHasBookKey record, @Param("example") CateHasBookExample example);
}