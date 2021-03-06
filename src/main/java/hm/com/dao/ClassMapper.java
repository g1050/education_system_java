package hm.com.dao;

import hm.com.bean.Class;
import hm.com.bean.ClassExample;
import java.util.List;

import hm.com.bean.Major;
import hm.com.bean.MajorExample;
import org.apache.ibatis.annotations.Param;

public interface ClassMapper {
    long countByExample(ClassExample example);

    int deleteByExample(ClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Class record);

    int insertSelective(Class record);

    List<Class> selectByExample(ClassExample example);

    //包含具体学院和专业名字
    List<Class> selectByExampleWithMajorAndCollege(ClassExample example);

    Class selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Class record, @Param("example") ClassExample example);

    int updateByExample(@Param("record") Class record, @Param("example") ClassExample example);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
}