package hm.com.service;

import hm.com.bean.*;
import hm.com.dao.ClassMapper;
import hm.com.dao.CourseMapper;
import hm.com.dao.CourseToStudentMapper;
import hm.com.dao.CourseToTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseToTeacherMapper courseToTeacherMapper;
    @Autowired
    CourseToStudentMapper courseToStudentMapper;

    public List<Course> getAll(){
        //1.从数据库获取信息
        List <Course> list = courseMapper.selectByExample(null);
        //返回
        return list;
    }

    public void addCourse(Course course) {
        courseMapper.insertSelective(course);
        return ;
    }

    public int updateCourse(Course course) {
        return courseMapper.updateByPrimaryKey(course);
    }

    public int deleteCourse(Integer id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    public int deleteCourse(List<Integer> ids){
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return courseMapper.deleteByExample(example);
    }

    public List<Course> getCourseByTeacherId(Integer teacherId) {
        //获取该教师对应的所有课程的Id
        CourseToTeacherExample courseToTeacherExample = new CourseToTeacherExample();
        CourseToTeacherExample.Criteria criteria1 = courseToTeacherExample.createCriteria();
        criteria1.andTeacherIdEqualTo(teacherId);
        List<CourseToTeacher> courseToTeachers = courseToTeacherMapper.selectByExample(courseToTeacherExample);

        //获得ids
        List<Integer> ids = new ArrayList<Integer>();
        for(CourseToTeacher c: courseToTeachers){
            ids.add(c.getCourseId());
        }


        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return courseMapper.selectByExample(example);
    }

    public List<Course> getCourseByStudentIdt(Integer studentId) {
        //获取该学生对应的所有课程的Id
        CourseToStudentExample courseToStudentExample = new CourseToStudentExample();
        CourseToStudentExample.Criteria criteria1 = courseToStudentExample.createCriteria();
        criteria1.andStudentIdEqualTo(studentId);

        List<CourseToStudent> courseToStudents = courseToStudentMapper.selectByExample(courseToStudentExample);

        //获得ids
        List<Integer> ids = new ArrayList<Integer>();
        for(CourseToStudent c: courseToStudents){
            ids.add(c.getCourseToTeacherId());
        }

        System.out.println(ids.toString());

        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return courseMapper.selectByExample(example);
//        return null;
    }
}
