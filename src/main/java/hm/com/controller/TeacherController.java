package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Club;
import hm.com.bean.CourseToTeacher;
import hm.com.bean.Teacher;
import hm.com.util.ReturnMessage;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import hm.com.service.TeacherService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    //获取教师分页模块
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getTeacher(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        System.out.println(limit);
        System.out.println(page);

        PageHelper.startPage(page, limit);
        //查询数据库
        List<Teacher> list = teacherService.getAll();
        //把list信息add到ReturnMessage后面
        PageInfo pageInfo = new PageInfo(list, 5);
        return ReturnMessage.success().add("pageInfo", pageInfo);
    }

    //获取所有老师信息
    @RequestMapping(value = "/all")
    @ResponseBody
    public ReturnMessage getAllTeacher(){
        List<Teacher> list = teacherService.getAll();
        return ReturnMessage.success().add("teacher",list);
    }

    //@RequestBoby 注解 ->解析json
    //向数据库添加数据
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addTeacher(@RequestBody Teacher teacher){
        teacherService.addTeacher(teacher);
        return ReturnMessage.success();
    }

    //更新teacher
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateTeacher(@RequestBody Teacher teacher){
        int res = teacherService.updateTeacher(teacher);
        if(res == 1){
            return ReturnMessage.success();
        }else {
            return ReturnMessage.fail();
        }
    }

    //删除teacher 单个And批量
    @RequestMapping(value = "/{ids",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteTeacher(@PathVariable("ids")String ids){
        //if else 判断请求是单个or批量删除
        if(ids.contains("-")){
            //String List
            String[] strIds = ids.split("-");
            //构建delIds 数组 Integer
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                delIds.add(Integer.parseInt(string));
            }
            //传给service层
            teacherService.deteleTeachers(delIds);
            return ReturnMessage.success();
        }else{
            //单个删除
            teacherService.deteleTeacher(Integer.parseInt(ids));
            return ReturnMessage.success();
        }
    }
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

    //按照学院查询
    @RequestMapping(value = "/bycollege",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getTeacherByCollege(@RequestParam(value = "searchParams")String searchParams,
                                             @RequestParam(value = "page",defaultValue = "")Integer page,
                                             @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);

        String college = map.get("college");
        System.out.println(limit);
        System.out.println(page);

        List<Teacher> teachers = null;

        if(college.equals("")){//返回全部数据
            teachers = teacherService.getAll();
        }else{//查询返回
            teachers = teacherService.getTeacherByCollege(college);
        }
        //引入pageHelper插件
        PageHelper.startPage(page,limit);
        //包装一下数据
        PageInfo pageInfo = new PageInfo(teachers,5);
        System.out.println(college);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }


}
