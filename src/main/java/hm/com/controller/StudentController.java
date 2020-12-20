package hm.com.controller;

import hm.com.bean.Teacher;
import hm.com.util.Constant;
import hm.com.util.ReturnMessage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Student;
import hm.com.service.StudentService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(Constant.PREFIX+"/student")
public class StudentController {
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
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //添加
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addStudent(@RequestBody Student student)
    {
        studentService.addStudent(student);
        return ReturnMessage.success();
    }

    //删除
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteStudent(@PathVariable("ids")String ids) {
        //if else判断单个删除或者多个删除

        if (ids.contains("-")) {
            String[] strIds = ids.split("-");
            List<Integer> delIds = new ArrayList<Integer>();
            //构建delIds 数组 Integer
            for (String string : strIds) {
                delIds.add(Integer.parseInt(string));
            }
            //传给service层
            studentService.deleteStudents(delIds);
            return ReturnMessage.success();
        } else {
            //单个删除
            studentService.deleteStudent(Integer.parseInt(ids));
            return ReturnMessage.success();
        }
    }
    //更新student
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateStudent(@RequestBody Student student){
        int res = studentService.updateStduent(student);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
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

        List<Student> students = null;

        if(college.equals("")){//返回全部数据
            students = studentService.getAll();
        }else{//查询返回
            students = studentService.getStudentByCollege(college);
        }
        //引入pageHelper
        PageHelper.startPage(page,limit);
        //包装一下数据
        PageInfo pageInfo = new PageInfo(students,5);
        System.out.println(college);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }
    //姓名查询
    @RequestMapping(value = "/byname",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getStudentByName(@RequestParam(value = "searchParams")String searchParams,
                                          @RequestParam(value = "page",defaultValue = "")Integer page,
                                          @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);
        String studentName = map.get("name");
        System.out.println("page");
        System.out.println("limit");

        List<Student> students = null;
        if (studentName.equals("")){
            students = studentService.getAll();
        }else{
            students = studentService.getStudentByName(studentName);
        }
        //引入pageHelper插件
        PageHelper.startPage(page,limit);
        //包装一下数据
        PageInfo pageInfo = new PageInfo(students,5);
        System.out.println(studentName);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //多条件查询
    @RequestMapping(value = "/bymore",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getStudentByMore(@RequestParam(value = "searchParams")String searchParams,
                                             @RequestParam(value = "page",defaultValue = "")Integer page,
                                             @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);

        String college = map.get("college");
        String studentName = map.get("name");
        System.out.println(limit);
        System.out.println(page);

        List<Student> students = null;

        if(studentName.equals("")&&college.equals("")){
            students = studentService.getAll();
        }else if(studentName.equals("")){
            students = studentService.getStudentByCollege(college);
        }else if(college.equals("")){
            students = studentService.getStudentByName(studentName);
        }else{
            students = studentService.getStudentByMore(college,studentName);
        }
        //引入pageHelper
        PageHelper.startPage(page,limit);
        //包装一下数据
        PageInfo pageInfo = new PageInfo(students,5);
        System.out.println(college);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }
//    @RequestMapping(value = "/byname",method = RequestMethod.POST)
//    @ResponseBody
//    public ReturnMessage getClassByCollege(@RequestParam(value = "name",defaultValue = "")String name ){
//        List<Student>list = null;
//        if(name.equals("")){//返回全部数据
//            list = studentService.getAll();
//        }else{//查询返回
//            list = studentService.getStudentByName(name);
//        }
//        System.out.println(name);
//        return  ReturnMessage.success().add("pageInfo",list);
//    }



}
