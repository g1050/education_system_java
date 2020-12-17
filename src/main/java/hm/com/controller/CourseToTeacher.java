package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.service.CourseToTeacherService;
import hm.com.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/12/17 13:11
 * @description：老师和课程中间表
 * @modified By：
 * @version: $
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/c2t")
public class CourseToTeacher {
    @Autowired
    CourseToTeacherService courseToTeacherService;

    //老师选择教授的课程
    @RequestMapping(value = "/{teacherId}/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage selectCoures(@PathVariable("teacherId")Integer teacherId, @PathVariable("courseId")Integer courseId){
        courseToTeacherService.selectCourse(teacherId,courseId);
        return ReturnMessage.success();
    }

    //根据courseId获取所有的teacher
    //localhost:8080/api/teacher/bycourse/1
    @RequestMapping(value = "/bycourse/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getTeacherByCourseId(@PathVariable("courseId")Integer courseId,
                                              @RequestParam("page")Integer page,
                                              @RequestParam("limit")Integer limit){

        PageHelper.startPage(page,limit);
        //根据courseId获取所有老师
        List<hm.com.bean.CourseToTeacher> list = courseToTeacherService.getTeacherByCourseId(courseId);
        PageInfo pageInfo = new PageInfo(list,5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //localhost:8080/api/c2t/id
    //根据主键删除
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteCourseToTeacherBy(@PathVariable("id")Integer id){
        courseToTeacherService.deleteCourse2Teacher(id);
        return ReturnMessage.success();
    }
}
