package edu.scau.mapper;

import edu.scau.model.User;
import edu.scau.model.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //注解写法

    //宏定义
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " userid, password ";
    String SELECT_FIELDS = " name, defaddr, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values(#{userid},#{password})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where userId=#{userid}"})
    User selectById(String userId);

//    @Select({"select ", INSERT_FIELDS, " from ", TABLE_NAME, " where name=#{username}"})
//    User selectByName(String name);

    @Update({"update ", TABLE_NAME, " set password=#{password} where userId=#{userId}"})
    void updatePassword(User user);

    @Delete({"delete from ", TABLE_NAME, " where userId=#{userId}"})
    void deleteById(String userId);



/*  接口示例
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
*/
}