package hm.com.dao;

import hm.com.bean.Domnitory;
import hm.com.bean.DomnitoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DomnitoryMapper {
    long countByExample(DomnitoryExample example);

    int deleteByExample(DomnitoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Domnitory record);

    int insertSelective(Domnitory record);

    List<Domnitory> selectByExample(DomnitoryExample example);

    Domnitory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Domnitory record, @Param("example") DomnitoryExample example);

    int updateByExample(@Param("record") Domnitory record, @Param("example") DomnitoryExample example);

    int updateByPrimaryKeySelective(Domnitory record);

    int updateByPrimaryKey(Domnitory record);
}