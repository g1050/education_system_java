package hm.com.service;

import hm.com.bean.*;
import hm.com.dao.CourseToTeacherMapper;
import hm.com.dao.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/12/15 18:40
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    CourseToTeacherMapper courseToTeacherMapper;

    public List<Teacher> getAll() {
        List<Teacher> list = teacherMapper.selectByExampleWithCollege(null);
        return list;
    }

    public void addTeacher(Teacher teacher){
        teacherMapper.insertSelective(teacher);
        return;
    }

    public  int updateTeacher(Teacher teacher){
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    public int deteleTeacher(Integer id){
        return teacherMapper.deleteByPrimaryKey(id);
    }

    public int deteleTeachers(List<Integer> ids){
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return teacherMapper.deleteByExample(example);
    }

    //插入学生选课记录
    public void selectCourse(Integer teacherId, Integer courseId) {
        CourseToTeacher courseToTeacher = new CourseToTeacher();
        courseToTeacher.setCourseId(courseId);
        courseToTeacher.setTeacherId(teacherId);

        courseToTeacherMapper.insertSelective(courseToTeacher);
    }

    public List<CourseToTeacher> getTeacherByCourseId(Integer courseId) {
//        List<Integer> ids = new ArrayList<Integer>();
//        //根据courseId获取所有teacherId
//        CourseToTeacherExample example = new CourseToTeacherExample();
//        CourseToTeacherExample.Criteria criteria = example.createCriteria();
//        criteria.andCourseIdEqualTo(courseId);
//        List<CourseToTeacher> courseToTeachers = courseToTeacherMapper.selectByExample(example);
//
//        System.out.println(courseToTeachers.toString());
//        for(CourseToTeacher res : courseToTeachers){
//            ids.add(res.getTeacherId());
//        }
//
//        System.out.println("ids = " + ids.toString());
//        //根据teacherId查出所有teacher
//        TeacherExample teacherExample = new TeacherExample();
//        TeacherExample.Criteria criteria1 = teacherExample.createCriteria();
//        criteria1.andIdIn(ids);

        return courseToTeacherMapper.selectByCouseId(courseId);
    }

    public void deleteCourse2Teacher(Integer courseId, Integer teacherId) {
        CourseToTeacherExample example = new CourseToTeacherExample();
        CourseToTeacherExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId).andTeacherIdEqualTo(teacherId);
        courseToTeacherMapper.deleteByExample(example);
        return;
    }

    //学院查询
    public List<Teacher> getTeacherByCollege(String college) {
        List<Teacher> all = getAll();
        List<Teacher> list = new ArrayList<Teacher>();

        for(Teacher teacher : all){
            if(teacher.getCollege().getName().equals(college)){
                list.add(teacher);
            }
        }
        return list;
    }
    //姓名查询
    public List<Teacher> getTeacherByName(String teacherName) {
        List<Teacher> all = getAll();
        List<Teacher> list = new ArrayList<Teacher>();

        for(Teacher teacher : all){
            if(teacher.getName().equals(teacherName)){
                System.out.println(teacher.getName());
                list.add(teacher);
            }
        }
        return list;
    }

    //多条件查询
    public List<Teacher> getTeacherByMore(String college,String teacherName){
        List<Teacher> all = getAll();
        List<Teacher> list = new ArrayList<Teacher>();

        for(Teacher teacher : all){
            if(teacher.getCollege().getName().equals(college) && teacher.getName().equals(teacherName)){
                list.add(teacher);
            }
        }
        return list;
    }

}
