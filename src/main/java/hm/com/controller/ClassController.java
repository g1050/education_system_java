package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Class;
import hm.com.bean.Dormitory;
import hm.com.util.ReturnMessage;
import hm.com.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ��sky
 * @date ��Created in 2020/11/22 14:21
 * @description���༶������
 * @modified By��
 * @version: 0.1$
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/class")
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
}
