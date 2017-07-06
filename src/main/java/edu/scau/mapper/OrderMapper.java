package edu.scau.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.scau.model.Order;
import edu.scau.model.OrderExample;

@Mapper
public interface OrderMapper {
    //@Select({"select * ", " from ", "`order`" , " where userId=#{userid} order by orderid desc limit 0, 1 " })
	Order selectLatestOrder(@Param("userid") String userId, @Param("offset") int offset,
                            @Param("limit") int limit);
	
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer orderid);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer orderid);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}