package hm.com.service;

import hm.com.bean.CourseToStudent;
import hm.com.bean.CourseToStudentExample;
import hm.com.bean.CourseToTeacher;
import hm.com.dao.CourseToStudentMapper;
import hm.com.dao.CourseToTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/12/19 20:30
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class CourseToStudentService {
    @Autowired
    CourseToStudentMapper courseToStudentMapper;
    @Autowired
    CourseToTeacherMapper courseToTeacherMapper;

    public void selectCourse(Integer studentId, Integer c2tId) {
        //插入学生选课记录,根据course_to_teacher的id选课
        CourseToStudent courseToStudent = new CourseToStudent();
        courseToStudent.setCourseToTeacherId(c2tId);
        courseToStudent.setStudentId(studentId);

        courseToStudentMapper.insert(courseToStudent);
    }

    public List<CourseToStudent> getCourseByStudentId(Integer studentId) {

        return courseToStudentMapper.selectByExampleWithCourseAndTeacher(studentId);
    }
}
