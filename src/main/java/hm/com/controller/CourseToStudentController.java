package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.CourseToStudent;
import hm.com.service.CourseToStudentService;
import hm.com.util.Constant;
import hm.com.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ��sky
 * @date ��Created in 2020/12/19 20:29
 * @description��ѧ��ѡ��
 * @modified By��
 * @version: $
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(Constant.PREFIX+"/c2s")
public class CourseToStudentController {
    @Autowired
    CourseToStudentService courseToStudentService;

    //ѧ��ѡ��
    //localhost:8080/api/c2s/selectcourse/2
    @RequestMapping(value = "/selectcourse/{c2tId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage selectCoures(HttpServletRequest request,
                                      @PathVariable("c2tId")Integer c2tId){
        Integer studentId = (Integer) request.getAttribute(Constant.OLDID);
        courseToStudentService.selectCourse(studentId,c2tId);
        return ReturnMessage.success();
    }

    //����ѧ��Id��ȡѧ��ѡ�����пγ�
    //localhost:8080/api/c2s/course/28
    @RequestMapping(value = "/course/bystudent",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getCourseByStudentId(HttpServletRequest request,
                                              @RequestParam(value = "page",defaultValue = "1")Integer page,
                                              @RequestParam(value = "limit",defaultValue = "5")Integer limit){
        Integer studentId = (Integer) request.getAttribute(Constant.OLDID);
        PageHelper.startPage(page,limit);
        //����courseId��ȡ������ʦ
        List<CourseToStudent> list = courseToStudentService.getCourseByStudentId(studentId);
        PageInfo pageInfo = new PageInfo(list,limit);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //����c2tid��ȡ����ѧ��,�����ɼ���Ϣ
    //localhost:8080/api/c2s/student/byc2t/2
    @RequestMapping(value = "/student/byc2t/{c2tId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getStudentByC2tId(@PathVariable("c2tId")Integer c2tId,
                                              @RequestParam(value = "page",defaultValue = "1")Integer page,
                                              @RequestParam(value = "limit",defaultValue = "5")Integer limit){
        PageHelper.startPage(page,limit);
        //����courseId��ȡ������ʦ
        List<CourseToStudent> list = courseToStudentService.getStudentByC2tId(c2tId);
        PageInfo pageInfo = new PageInfo(list,limit);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //localhost:8080/api/c2s/id
    //��������ɾ��
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteCourseToTeacher(@PathVariable("id")Integer id){
        courseToStudentService.deleteCourse2Teacher(id);
        return ReturnMessage.success();
    }
}
