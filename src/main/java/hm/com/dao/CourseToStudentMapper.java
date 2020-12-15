package hm.com.dao;

import hm.com.bean.CourseToStudent;
import hm.com.bean.CourseToStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseToStudentMapper {
    long countByExample(CourseToStudentExample example);

    int deleteByExample(CourseToStudentExample example);

    int insert(CourseToStudent record);

    int insertSelective(CourseToStudent record);

    List<CourseToStudent> selectByExample(CourseToStudentExample example);

    int updateByExampleSelective(@Param("record") CourseToStudent record, @Param("example") CourseToStudentExample example);

    int updateByExample(@Param("record") CourseToStudent record, @Param("example") CourseToStudentExample example);
}