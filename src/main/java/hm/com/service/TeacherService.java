package hm.com.service;

import hm.com.bean.CourseToTeacher;
import hm.com.dao.CourseToTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ��sky
 * @date ��Created in 2020/12/15 18:40
 * @description��
 * @modified By��
 * @version: $
 */
@Service
public class TeacherService {

    @Autowired
    CourseToTeacherMapper courseToTeacherMapper;

    //����ѧ��ѡ�μ�¼
    public void selectCourse(Integer teacherId, Integer courseId) {
        CourseToTeacher courseToTeacher = new CourseToTeacher();
        courseToTeacher.setCourseId(courseId);
        courseToTeacher.setTeacherId(teacherId);

        courseToTeacherMapper.insert(courseToTeacher);
    }
}
