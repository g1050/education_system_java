package hm.com.dao;

import hm.com.bean.CourseToTeacher;
import hm.com.bean.CourseToTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseToTeacherMapper {
    long countByExample(CourseToTeacherExample example);

    int deleteByExample(CourseToTeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseToTeacher record);

    int insertSelective(CourseToTeacher record);

    List<CourseToTeacher> selectByExample(CourseToTeacherExample example);

    List<CourseToTeacher> selectByCouseId(@Param("courseId") Integer courseId);

    CourseToTeacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseToTeacher record, @Param("example") CourseToTeacherExample example);

    int updateByExample(@Param("record") CourseToTeacher record, @Param("example") CourseToTeacherExample example);

    int updateByPrimaryKeySelective(CourseToTeacher record);

    int updateByPrimaryKey(CourseToTeacher record);
}