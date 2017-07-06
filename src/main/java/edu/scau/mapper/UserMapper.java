package edu.scau.mapper;

import org.apache.ibatis.annotations.*;

import edu.scau.model.User;

@Mapper
public interface UserMapper {
    //娉ㄨВ鍐欐硶

    //瀹忓畾涔�
    final static String TABLE_NAME = " user ";
    final static String INSERT_FIELDS = " userid, name, password ";

    final static String SELECT_FIELDS = INSERT_FIELDS + ", defaddr ";


    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values(#{userid},#{name},#{password})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where userId=#{userid}"})
    User selectById(String userid);

//    @Select({"select ", INSERT_FIELDS, " from ", TABLE_NAME, " where name=#{username}"})
//    User selectByName(String name);

    @Update({"update ", TABLE_NAME, " set password=#{password} where userId=#{userId}"})
    void updatePassword(User user);

    @Delete({"delete from ", TABLE_NAME, " where userId=#{userId}"})
    void deleteById(String userId);



/*  鎺ュ彛绀轰緥
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