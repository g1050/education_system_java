package hm.com.controller;

import hm.com.bean.CourseToTeacher;
import hm.com.bean.Teacher;
import hm.com.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import hm.com.service.TeacherService;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    //老师选择教授的课程
    @RequestMapping(value = "/course/{teacherId}/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage selectCoures(@PathVariable("teacherId")Integer teacherId,@PathVariable("courseId")Integer courseId){
        teacherService.selectCourse(teacherId,courseId);
        return ReturnMessage.success();
    }

    //根据courseId获取所有的teacher
    //localhost:8080/api/teacher/bycourse/1
    @RequestMapping(value = "/bycourse/{courseId}")
    @ResponseBody
    public ReturnMessage getTeacherByCourseId(@PathVariable("courseId")Integer courseId){
        System.out.println(courseId);

        //感觉courseId获取所有老师
        List<CourseToTeacher> list = teacherService.getTeacherByCourseId(courseId);
        //返回给controller

        return ReturnMessage.success().add("list",list);
    }

    //localhost:8080/api/deletec2t/id
    @RequestMapping(value = "/deletec2t/{courseid}/{teacherId}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteCourseToTeacherBy(@PathVariable("courseid")Integer courseid,
                                                 @PathVariable("teacherId")Integer teacherId){
        teacherService.deleteCourse2Teacher(courseid,teacherId);
        return ReturnMessage.success();
    }
}
