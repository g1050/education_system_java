package hm.com.service;

import hm.com.bean.Course;
import hm.com.bean.CourseToTeacher;
import hm.com.bean.CourseToTeacherExample;
import hm.com.dao.CourseToTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ��sky
 * @date ��Created in 2020/12/17 13:13
 * @description��
 * @modified By��
 * @version: $
 */
@Service
public class CourseToTeacherService {
    @Autowired
    CourseToTeacherMapper courseToTeacherMapper;

    //����ѧ��ѡ�μ�¼
    public void selectCourse(Integer teacherId, Integer courseId) {
        CourseToTeacher courseToTeacher = new CourseToTeacher();
        courseToTeacher.setCourseId(courseId);
        courseToTeacher.setTeacherId(teacherId);

        courseToTeacherMapper.insertSelective(courseToTeacher);
    }

    public List<CourseToTeacher> getTeacherByCourseId(Integer courseId) {
        return courseToTeacherMapper.selectByCouseId(courseId);
    }

    public void deleteCourse2Teacher(Integer id) {
        courseToTeacherMapper.deleteByPrimaryKey(id);
        return;
    }


    public List<CourseToTeacher> getCourseByTeacherId(Integer teacherId) {
        return courseToTeacherMapper.selectByTeacherId(teacherId);

    }

    //��ȡ���е�ѡ�޿ε���ʦ�Ϳγ���Ϣ
    public List<CourseToTeacher> getAllNotRequiredCourse() {
        return courseToTeacherMapper.selectAllNotRequiredCourse();
    }
}
