package hm.com.controller;

import hm.com.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import hm.com.service.TeacherService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    //��ʦѡ����ڵĿγ�
    @RequestMapping(value = "/course/{teacherId}/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage selectCoures(@PathVariable("teacherId")Integer teacherId,@PathVariable("courseId")Integer courseId){
        teacherService.selectCourse(teacherId,courseId);
        return ReturnMessage.success();
    }
}
