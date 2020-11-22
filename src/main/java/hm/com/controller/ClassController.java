package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Class;
import hm.com.bean.ReturnMessage;
import hm.com.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ��sky
 * @date ��Created in 2020/11/22 14:21
 * @description���༶������
 * @modified By��
 * @version: 0.1$
 */
@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;

    //localhost:8080/api/class
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    //Url -> Controller -> Service -> Mapper
    public ReturnMessage test(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "limit",defaultValue = "100")Integer limit){
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

}
