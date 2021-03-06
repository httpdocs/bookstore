package edu.scau.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.scau.model.Cate;
import edu.scau.model.CateExample;

@Mapper
public interface CateMapper {
	
	@Select({"select * from cate"})
	List<Cate> list();

    int countByExample(CateExample example);

    int deleteByExample(CateExample example);

    int deleteByPrimaryKey(String cate);

    int insert(Cate record);

    int insertSelective(Cate record);

    List<Cate> selectByExample(CateExample example);

    Cate selectByPrimaryKey(String cate);

    int updateByExampleSelective(@Param("record") Cate record, @Param("example") CateExample example);

    int updateByExample(@Param("record") Cate record, @Param("example") CateExample example);

    int updateByPrimaryKeySelective(Cate record);

    int updateByPrimaryKey(Cate record);
}