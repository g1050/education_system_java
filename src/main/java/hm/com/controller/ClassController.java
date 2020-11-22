package hm.com.controller;

import hm.com.bean.Class;
import hm.com.bean.ReturnMessage;
import hm.com.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    //localhost:8080/api/ class/test
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    //Url -> Controller -> Service -> Mapper
    public ReturnMessage test(){
        //�յ�����

        //�����ݿ��в�ѯ����
        List<Class> list;
        list = classService.getAll();
        //�����ݸ�ǰ��
        return ReturnMessage.success().add("data",list);
        //code = 0 message= edtend{test:zy,pageInfo,...}
    }

}
