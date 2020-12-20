package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.service.CourseToTeacherService;
import hm.com.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ��sky
 * @date ��Created in 2020/12/17 13:11
 * @description����ʦ�Ϳγ��м��
 * @modified By��
 * @version: $
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/c2t")
public class CourseToTeacherController {
    @Autowired
    CourseToTeacherService courseToTeacherService;

    //��ʦѡ����ڵĿγ�
    @RequestMapping(value = "/selectCourse",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage selectCoures(@RequestParam("id")Integer courseId, @RequestParam("select")String select){
        System.out.println(select);
        System.out.println(courseId);

        if(select.contains(",")){//����ɾ��
            //String List
            String[] strIds = select.split(",");
            //����delIds ���� Integer
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                Integer teacherId = Integer.parseInt(string);
                //System.out.println(teacherId);
                courseToTeacherService.selectCourse(teacherId,courseId);
            }
            return ReturnMessage.success();
        }else{//����ɾ��
            //���Ҫɾ���û���id String->Integer
            Integer teacherId = Integer.parseInt(select);
            courseToTeacherService.selectCourse(teacherId,courseId);
            return ReturnMessage.success();
        }

    }

    //����courseId��ȡ���е�teacher
    //localhost:8080/api/teacher/bycourse/1
    @RequestMapping(value = "/teacher/bycourse/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getTeacherByCourseId(@PathVariable("courseId")Integer courseId,
                                              @RequestParam(value = "page",defaultValue = "1")Integer page,
                                              @RequestParam(value = "limit",defaultValue = "1")Integer limit){

        PageHelper.startPage(page,limit);
        //����courseId��ȡ������ʦ
        List<hm.com.bean.CourseToTeacher> list = courseToTeacherService.getTeacherByCourseId(courseId);
        PageInfo pageInfo = new PageInfo(list,5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //localhost:8080/api/c2t/id
    //��������ɾ��
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteCourseToTeacher(@PathVariable("id")Integer id){
        courseToTeacherService.deleteCourse2Teacher(id);
        return ReturnMessage.success();
    }

    //������ʦid��ȡ���еĿγ���Ϣ
    //localhost:8080/api/c2t/course/byteacher/1
    @RequestMapping(value = "/course/byteacher/{teacherId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getCourseByTeacherId(@PathVariable("teacherId")Integer teacherId,
                                              @RequestParam(value = "page",defaultValue = "1")Integer page,
                                              @RequestParam(value = "limit",defaultValue = "5")Integer limit){

        PageHelper.startPage(page,limit);
        //����teacherId��ȡ����course
        List<hm.com.bean.CourseToTeacher> list = courseToTeacherService.getCourseByTeacherId(teacherId);
        PageInfo pageInfo = new PageInfo(list,5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //��ȡ���е�ѡ�޿���Ϣ
    //localhost:8080/api/c2t/course/notrequired
    @RequestMapping(value = "/course/notrequired",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getAllNotRequiredCourse(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                 @RequestParam(value = "limit",defaultValue = "5")Integer limit){

        PageHelper.startPage(page,limit);
        //����teacherId��ȡ����course
        List<hm.com.bean.CourseToTeacher> list = courseToTeacherService.getAllNotRequiredCourse();
        PageInfo pageInfo = new PageInfo(list,5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

}
