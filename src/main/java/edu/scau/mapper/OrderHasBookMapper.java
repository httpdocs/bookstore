package edu.scau.mapper;

import edu.scau.model.OrderHasBook;
import edu.scau.model.OrderHasBookExample;
import edu.scau.model.OrderHasBookKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrderHasBookMapper {
    int countByExample(OrderHasBookExample example);

    int deleteByExample(OrderHasBookExample example);

    int deleteByPrimaryKey(OrderHasBookKey key);

    int insert(OrderHasBook record);

    int insertSelective(OrderHasBook record);

    List<OrderHasBook> selectByExample(OrderHasBookExample example);

    OrderHasBook selectByPrimaryKey(OrderHasBookKey key);

    int updateByExampleSelective(@Param("record") OrderHasBook record, @Param("example") OrderHasBookExample example);

    int updateByExample(@Param("record") OrderHasBook record, @Param("example") OrderHasBookExample example);

    int updateByPrimaryKeySelective(OrderHasBook record);

    int updateByPrimaryKey(OrderHasBook record);
}