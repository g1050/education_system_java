package hm.com.dao;

import hm.com.bean.Class;
import hm.com.bean.ClassExample;
import hm.com.bean.Club;
import hm.com.bean.ClubExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClubMapper {
    long countByExample(ClubExample example);

    int deleteByExample(ClubExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Club record);

    int insertSelective(Club record);

    List<Club> selectByExample(ClubExample example);

    //包含具体学院名字
    List<Club> selectByExampleWithCollege(ClubExample example);

    Club selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Club record, @Param("example") ClubExample example);

    int updateByExample(@Param("record") Club record, @Param("example") ClubExample example);

    int updateByPrimaryKeySelective(Club record);

    int updateByPrimaryKey(Club record);
}