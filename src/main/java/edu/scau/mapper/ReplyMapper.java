package edu.scau.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.scau.model.Reply;
import edu.scau.model.ReplyExample;

public interface ReplyMapper {
    int countByExample(ReplyExample example);

    int deleteByExample(ReplyExample example);

    int deleteByPrimaryKey(Integer replyid);

    int insert(Reply record);

    int insertSelective(Reply record);

    List<Reply> selectByExample(ReplyExample example);

    Reply selectByPrimaryKey(Integer replyid);

    int updateByExampleSelective(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByExample(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);
}