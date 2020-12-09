package hm.com.controller;


import hm.com.util.ReturnMessage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Course;
import hm.com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addCourse(@RequestBody Course course)
    {
        courseService.addCourse(course);
        return ReturnMessage.success();
    }

}
