package hm.com.service;

import hm.com.bean.CourseToTeacher;
import hm.com.bean.CourseToTeacherExample;
import hm.com.bean.Teacher;
import hm.com.bean.TeacherExample;
import hm.com.dao.CourseToTeacherMapper;
import hm.com.dao.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author £ºsky
 * @date £ºCreated in 2020/12/15 18:40
 * @description£º
 * @modified By£º
 * @version: $
 */
@Service
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;
}
