package hm.com.controller;


import hm.com.bean.Club;
import hm.com.bean.Teacher;
import hm.com.util.ReturnMessage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Course;
import hm.com.service.CourseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
@RequestMapping("/course")
public class CourseController {

    @Autowired  //自动注入函数内容
    CourseService courseService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage test(@RequestParam(value="page", defaultValue = "1")   Integer page,
                              @RequestParam(value = "limit",defaultValue = "100") Integer limit){
            System.out.println(page);
            System.out.println(limit);
        PageHelper.startPage(page,limit);
        List<Course> list = courseService.getAll();
            PageInfo pageInfo = new PageInfo(list, 5);
            return ReturnMessage.success().add("pageInfo",pageInfo);
        //从数据库获取信息
        //返回信息
    }

    //获取所有选修课程
    //localhost:8080/api/course/notrequired
    @RequestMapping(value = "/notrequired",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getNotRequiredCourse(@RequestParam(value="page", defaultValue = "1")   Integer page,
                              @RequestParam(value = "limit",defaultValue = "100") Integer limit){
        System.out.println(page);
        System.out.println(limit);
        PageHelper.startPage(page,limit);
        List<Course> list = courseService.getAllNotRequired();
        PageInfo pageInfo = new PageInfo(list, 5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
        //从数据库获取信息
        //返回信息
    }

    //获取所有课程信息
    @RequestMapping(value = "/all")
    @ResponseBody
    public ReturnMessage getAllCourse(){
        List<Course> list = courseService.getAll();
        return ReturnMessage.success().add("course",list);
    }
    //向数据库添加数据
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addCourse(@RequestBody Course course)
    {
        courseService.addCourse(course);
        return ReturnMessage.success();
    }

    //更新课程
    @RequestMapping(value="",method=RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateCourse(@RequestBody Course course){
    int res = courseService.updateCourse(course);
    if (res==1){
        return ReturnMessage.success();
    }
    else{
        return ReturnMessage.fail();
    }
    }

    //删除课程 批量单个二合一
    //单个 前端发送请求localhost:8080/api/college/+删除的id号
    //多个 前端发送请求localhost:8080/api/college/+删除的“01-02-03-06”字符串
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteCourse(@PathVariable("ids")String ids)
    {
    if (ids.contains("-")){ //删除多个课程
        String[] strIds = ids.split("-");
        //构建delIds 数组
        List<Integer> delIds = new ArrayList<Integer>();
        for (String string : strIds){
            delIds.add(Integer.parseInt(string));
        }
        //传给service层
        courseService.deleteCourse(delIds);
        return ReturnMessage.success();


    }
    else{
        //单个删除
        courseService.deleteCourse(Integer.parseInt(ids));
        return ReturnMessage.success();
    }
    }

    //搜索查询
    @RequestMapping(value = "/bycollege",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getClubByCollege(@RequestParam(value = "searchParams")String searchParams,
                                          @RequestParam(value = "page",defaultValue = "")Integer page,
                                          @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);

        String college = map.get("college");
        System.out.println(limit);
        System.out.println(page);

        List<Course> courses = null;

        if(college.equals("")){//返回全部数据
            courses = courseService.getAll();
        }else{//只返回所查字段数据
            courses = courseService.getCourseByCollege(college);
        }
        //引入pageHelper插件
        PageHelper.startPage(page,limit);
        //包装一下数据
        PageInfo pageInfo = new PageInfo(courses,5);
        System.out.println(college);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }
    //多条件查询
    @RequestMapping(value = "/bymore",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getCourseByMore(@RequestParam(value = "searchParams")String searchParams,
                                          @RequestParam(value = "page",defaultValue = "")Integer page,
                                          @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);
        String college = map.get("college");
        String courseName = map.get("name");
        System.out.println("page");
        System.out.println("limit");

        List<Course> courses = null;
        if(courseName.equals("")&&college.equals("")){
            courses = courseService.getAll();
        }else if(courseName.equals("")){
            courses = courseService.getCourseByCollege(college);
        }else if(college.equals("")){
            courses = courseService.getCourseByName(courseName);
        }else{
            courses = courseService.getCourseByMore(college,courseName);
        }

        PageHelper.startPage(page,limit);

        PageInfo pageInfo = new PageInfo(courses,5);

        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    @RequestMapping(value = "/teacher/{teacherId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getCourseByTeacherId(@PathVariable("teacherId")Integer teacherId,
                                              @RequestParam(value="page", defaultValue = "1")   Integer page,
                                              @RequestParam(value = "limit",defaultValue = "100") Integer limit)
    {
        PageHelper.startPage(page,limit);
        List<Course> list = courseService.getCourseByTeacherId(teacherId);
        PageInfo pageInfo = new PageInfo(list, 5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    @RequestMapping(value = "/student/{studentId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getCourseByStudentId(@PathVariable("studentId")Integer studentId,
                                              @RequestParam(value="page", defaultValue = "1")   Integer page,
                                              @RequestParam(value = "limit",defaultValue = "100") Integer limit)
    {
        PageHelper.startPage(page,limit);
        List<Course> list = courseService.getCourseByStudentIdt(studentId);
        PageInfo pageInfo = new PageInfo(list, 5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }
}
