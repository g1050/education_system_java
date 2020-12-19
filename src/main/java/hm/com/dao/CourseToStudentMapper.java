package hm.com.dao;

import hm.com.bean.CourseToStudent;
import hm.com.bean.CourseToStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseToStudentMapper {
    long countByExample(CourseToStudentExample example);

    int deleteByExample(CourseToStudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseToStudent record);

    int insertSelective(CourseToStudent record);

    List<CourseToStudent> selectByExample(CourseToStudentExample example);

    CourseToStudent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseToStudent record, @Param("example") CourseToStudentExample example);

    int updateByExample(@Param("record") CourseToStudent record, @Param("example") CourseToStudentExample example);

    int updateByPrimaryKeySelective(CourseToStudent record);

    int updateByPrimaryKey(CourseToStudent record);

    List<CourseToStudent> selectByExampleWithCourseAndTeacher(@Param("studentId")Integer studentId);
}