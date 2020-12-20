package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Class;
import hm.com.bean.Dormitory;
import hm.com.bean.Manager;
import hm.com.bean.Teacher;
import hm.com.util.Constant;
import hm.com.util.ReturnMessage;
import hm.com.service.ClassService;
//import net.sf.json.JSONObject;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ��sky
 * @date ��Created in 2020/11/22 14:21
 * @description���༶������
 * @modified By��
 * @version: 0.1$
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(Constant.PREFIX+"/class")
public class ClassController {

    @Autowired
    ClassService classService;

    //��ȡclass��ҳģ��
    //localhost:8080/api/class
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    //Url -> Controller -> Service -> Mapper
    public ReturnMessage getClass(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "limit",defaultValue = "100")Integer limit){
        //�յ�����

        //�����ݿ��в�ѯ����
        List<Class> list;
        list = classService.getAll();
        //�����ݸ�ǰ��
        //��װһ������
        //����pageHelper���
        PageHelper.startPage(page,limit);
        PageInfo pageInfo = new PageInfo(list,5);

        return ReturnMessage.success().add("pageInfo",pageInfo);
        //code = 0 message= edtend{test:zy,pageInfo,...}
    }

    //��ȡ�༶������Ϣ
    @RequestMapping(value = "/all")
    @ResponseBody
    public ReturnMessage getAllCollegeAndMajor(){
        List<Class> list = classService.getAll();
        return ReturnMessage.success().add("class",list);
    }

    //���class
    //RequestBodyע�⣬�Զ�����json-> class
    //localhost:8080/api/class POST
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addClass(@RequestBody Class aclass){
        //�յ�json����
        //������class����

        //����service�����class
        classService.addClass(aclass);
        return ReturnMessage.success();
    }

    //ɾ��class
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteClass(@PathVariable("ids")String ids){
        //System.out.println(id);
        //if else�жϵ���ɾ�����߶��ɾ��
        if(ids.contains("-")){
            //String List
            String[] strIds = ids.split("-");
            //����delIds ���� Integer
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                delIds.add(Integer.parseInt(string));
            }
            //����service��
            classService.deleteClasses(delIds);
            return ReturnMessage.success();
        }else{
            //����ɾ��
            classService.deleteClass(Integer.parseInt(ids));
            return ReturnMessage.success();
        }

    }

    //����class
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateClass(@RequestBody  Class classa){
        int res = classService.updateClass(classa);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }

    //����ѧԺ��ѯ
    @RequestMapping(value = "/bycollege",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getClassByCollege(@RequestParam(value = "college",defaultValue = "")String college ){
        List<Class>list = null;
        if(college.equals("")){//����ȫ������

        }else{//��ѯ����
             list = classService.getClassByCollege(college);
        }
        System.out.println(college);
        return  ReturnMessage.success().add("pageInfo",list);
    }
    //�������Ʋ�ѯ
    @RequestMapping(value = "/byname",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getClassByName(@RequestParam(value = "searchParams")String searchParams,
                                        @RequestParam(value = "page",defaultValue = "")Integer page,
                                        @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);

        String name = map.get("name");
        System.out.println("page");
        System.out.println("limit");

        List<Class> aclass = null;
        if(name.equals("")){//����ȫ������
            aclass = classService.getAll();
        }else{//��ѯ����
            aclass = classService.getClassByName(name);
        }

        //����pageHelper���
        PageHelper.startPage(page,limit);
        //��װһ������
        PageInfo pageInfo = new PageInfo(aclass,5);
        System.out.println(name);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //��������ѯ
    @RequestMapping(value = "/bymore",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getTeacherByMore(@RequestParam(value = "searchParams")String searchParams,
                                          @RequestParam(value = "page",defaultValue = "")Integer page,
                                          @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);
        String college = map.get("college");
        String className = map.get("name");
        System.out.println("page");
        System.out.println("limit");

        List<Class> classes = null;
        if(className.equals("")&&college.equals("")){
            classes = classService.getAll();
        }else if(className.equals("")){
            classes = classService.getClassByCollege(college);
        }else if(college.equals("")){
            classes = classService.getClassByName(className);
        }else{
            classes = classService.getClassByMore(college,className);
        }

        PageHelper.startPage(page,limit);

        PageInfo pageInfo = new PageInfo(classes,5);

        return ReturnMessage.success().add("pageInfo",pageInfo);
    }
}
