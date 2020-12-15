package hm.com.controller;
import hm.com.util.ReturnMessage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Student;
import hm.com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/student")
class StudentController {
    @Autowired
    StudentService studentService;

//    localhost:8080/api/student
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage test(@RequestParam(value = "page",defaultValue = "1")Integer page,
                              @RequestParam(value = "limit",defaultValue = "5")Integer limit) {

        System.out.println("收到class/test请求/123");
        //1.收到请求，到相应的函数
        //2.查询数据库，获取数据
        List<Student> data  = studentService.getAll();
        //3.把数据发送给前端
        PageHelper.startPage(page,limit);
        PageInfo pageInfo = new PageInfo(data,5);
        return ReturnMessage.success().add("test",pageInfo);
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addStudent(@RequestBody Student student)
    {
        studentService.addStudent(student);
        return ReturnMessage.success();
    }
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteStudent(@PathVariable("ids")String ids) {
        if (ids.contains("-")) {
            String[] strIds = ids.split("-");
            List<Integer> delIds = new ArrayList<Integer>();
            for (String string : strIds) {
                delIds.add(Integer.parseInt(string));
            }
            studentService.deleteStudents(delIds);
            return ReturnMessage.success();
        } else {
            studentService.deleteStudent(Integer.parseInt(ids));
            return ReturnMessage.success();
        }
    }


    //学生选课
    @RequestMapping(value = "/course/{studentId}/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage selectCoures(@PathVariable("studentId")Integer studentId,@PathVariable("courseId")Integer courseId){
        studentService.selectCourse(studentId,courseId);
        return ReturnMessage.success();
    }

}
