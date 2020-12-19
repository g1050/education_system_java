package hm.com.controller;
import hm.com.bean.Class;
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

        System.out.println("�յ�class/test����/123");
        //1.�յ����󣬵���Ӧ�ĺ���
        //2.��ѯ���ݿ⣬��ȡ����
        List<Student> data  = studentService.getAll();
        //3.�����ݷ��͸�ǰ��
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
        //if else�жϵ���ɾ�����߶��ɾ��

        if (ids.contains("-")) {
            String[] strIds = ids.split("-");
            List<Integer> delIds = new ArrayList<Integer>();
            //����delIds ���� Integer
            for (String string : strIds) {
                delIds.add(Integer.parseInt(string));
            }
            //����service��
            studentService.deleteStudents(delIds);
            return ReturnMessage.success();
        } else {
            //����ɾ��
            studentService.deleteStudent(Integer.parseInt(ids));
            return ReturnMessage.success();
        }
    }
    //����student
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateStudent(@RequestBody Student student){
        int res = studentService.updateStduent(student);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }




}
