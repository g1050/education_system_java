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

    //��ȡ��ʦ��ҳģ��
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getTeacher(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        System.out.println(limit);
        System.out.println(page);

        PageHelper.startPage(page, limit);
        //��ѯ���ݿ�
        List<Teacher> list = teacherService.getAll();
        //��list��Ϣadd��ReturnMessage����
        PageInfo pageInfo = new PageInfo(list, 5);
        return ReturnMessage.success().add("pageInfo", pageInfo);
    }

    //��ȡ������ʦ��Ϣ
    @RequestMapping(value = "/all")
    @ResponseBody
    public ReturnMessage getAllTeacher(){
        List<Teacher> list = teacherService.getAll();
        return ReturnMessage.success().add("teacher",list);
    }

    //@RequestBoby ע�� ->����json
    //�����ݿ��������
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addTeacher(@RequestBody Teacher teacher){
        teacherService.addTeacher(teacher);
        return ReturnMessage.success();
    }

    //����teacher
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

    //ɾ��teacher ����And����
    @RequestMapping(value = "/{ids",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteTeacher(@PathVariable("ids")String ids){
        //if else �ж������ǵ���or����ɾ��
        if(ids.contains("-")){
            //String List
            String[] strIds = ids.split("-");
            //����delIds ���� Integer
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                delIds.add(Integer.parseInt(string));
            }
            //����service��
            teacherService.deteleTeachers(delIds);
            return ReturnMessage.success();
        }else{
            //����ɾ��
            teacherService.deteleTeacher(Integer.parseInt(ids));
            return ReturnMessage.success();
        }
    }
    //��ʦѡ����ڵĿγ�
    @RequestMapping(value = "/course/{teacherId}/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage selectCoures(@PathVariable("teacherId")Integer teacherId,@PathVariable("courseId")Integer courseId){
        teacherService.selectCourse(teacherId,courseId);
        return ReturnMessage.success();
    }

    //����courseId��ȡ���е�teacher
    //localhost:8080/api/teacher/bycourse/1
    @RequestMapping(value = "/bycourse/{courseId}")
    @ResponseBody
    public ReturnMessage getTeacherByCourseId(@PathVariable("courseId")Integer courseId){
        System.out.println(courseId);

        //�о�courseId��ȡ������ʦ
        List<CourseToTeacher> list = teacherService.getTeacherByCourseId(courseId);
        //���ظ�controller

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

    //����ѧԺ��ѯ
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

        if(college.equals("")){//����ȫ������
            teachers = teacherService.getAll();
        }else{//��ѯ����
            teachers = teacherService.getTeacherByCollege(college);
        }
        //����pageHelper���
        PageHelper.startPage(page,limit);
        //��װһ������
        PageInfo pageInfo = new PageInfo(teachers,5);
        System.out.println(college);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }


}
