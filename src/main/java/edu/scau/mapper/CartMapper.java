package edu.scau.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.scau.model.Cart;
import edu.scau.model.CartExample;
import edu.scau.model.CartKey;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface CartMapper {
    @Select({"select ", " isbn ", " from ", " cart ", " where userId=#{id}"})
    List<String> getIsbns(String id);

    @Select({"select * from cart where userid=#{userid} and isbn=#{isbn}"})
    Cart selectOne(@Param("userid")String userid, @Param("isbn")String isbn);
    
    @Select({"select * from cart where userId=#{userId}"})
    List<Cart> selectByUser(@Param("userId")String userId);
    
    int countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(CartKey key);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(CartKey key);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}