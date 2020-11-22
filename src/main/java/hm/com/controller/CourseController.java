package hm.com.controller;

import hm.com.bean.ReturnMessage;
import hm.com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin("*")
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody

    public ReturnMessage test(){

        return ReturnMessage.success().add("function","ceshi");
    }
}
