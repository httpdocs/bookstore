package edu.scau.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.scau.model.CateHasBookExample;
import edu.scau.model.CateHasBookKey;

public interface CateHasBookMapper {
    int countByExample(CateHasBookExample example);

    int deleteByExample(CateHasBookExample example);

    int deleteByPrimaryKey(CateHasBookKey key);

    int insert(CateHasBookKey record);

    int insertSelective(CateHasBookKey record);

    List<CateHasBookKey> selectByExample(CateHasBookExample example);

    int updateByExampleSelective(@Param("record") CateHasBookKey record, @Param("example") CateHasBookExample example);

    int updateByExample(@Param("record") CateHasBookKey record, @Param("example") CateHasBookExample example);
}