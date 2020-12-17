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


}
