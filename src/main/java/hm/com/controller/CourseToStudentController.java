package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.CourseToStudent;
import hm.com.bean.CourseToTeacher;
import hm.com.service.CourseToStudentService;
import hm.com.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/12/19 20:29
 * @description：学生选课
 * @modified By：
 * @version: $
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/c2s")
public class CourseToStudentController {
    @Autowired
    CourseToStudentService courseToStudentService;

    //学生选课
    //localhost:8080/api/c2s/selectcourse/28/2
    @RequestMapping(value = "/selectcourse/{studentId}/{c2tId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage selectCoures(@PathVariable("studentId")Integer studentId, @PathVariable("c2tId")Integer c2tId){
        courseToStudentService.selectCourse(studentId,c2tId);
        return ReturnMessage.success();
    }

    //根据学生Id获取学生选的所有课程
    //localhost:8080/api/c2s/course/28
    @RequestMapping(value = "/course/bystudent/{studentId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getCourseByStudentId(@PathVariable("studentId")Integer studentId,
                                              @RequestParam(value = "page",defaultValue = "1")Integer page,
                                              @RequestParam(value = "limit",defaultValue = "5")Integer limit){
        PageHelper.startPage(page,limit);
        //根据courseId获取所有老师
        List<CourseToStudent> list = courseToStudentService.getCourseByStudentId(studentId);
        PageInfo pageInfo = new PageInfo(list,limit);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //根据c2tid获取所有学生
    //localhost:8080/api/c2s/student/byc2t/2
    @RequestMapping(value = "/student/byc2t/{c2tId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getStudentByC2tId(@PathVariable("c2tId")Integer c2tId,
                                              @RequestParam(value = "page",defaultValue = "1")Integer page,
                                              @RequestParam(value = "limit",defaultValue = "5")Integer limit){
        PageHelper.startPage(page,limit);
        //根据courseId获取所有老师
        List<CourseToStudent> list = courseToStudentService.getStudentByC2tId(c2tId);
        PageInfo pageInfo = new PageInfo(list,limit);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //localhost:8080/api/c2s/id
    //根据主键删除
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteCourseToTeacher(@PathVariable("id")Integer id){
        courseToStudentService.deleteCourse2Teacher(id);
        return ReturnMessage.success();
    }
}
