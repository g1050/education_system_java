package hm.com.dao;

import hm.com.bean.Dormitory;

import java.util.List;

import hm.com.bean.DormitoryExample;
import org.apache.ibatis.annotations.Param;

public interface DormitoryMapper {
    long countByExample(DormitoryExample example);

    int deleteByExample(DormitoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dormitory record);

    int insertSelective(Dormitory record);

    List<Dormitory> selectByExample(DormitoryExample example);

    Dormitory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Dormitory record, @Param("example") DormitoryExample example);

    int updateByExample(@Param("record") Dormitory record, @Param("example") DormitoryExample example);

    int updateByPrimaryKeySelective(Dormitory record);

    int updateByPrimaryKey(Dormitory record);
}