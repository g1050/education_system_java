package hm.com.controller;


import hm.com.bean.Major;
import hm.com.bean.ReturnMessage;
import hm.com.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/major")
public class MajorController {

    @Autowired
    MajorService majorService;
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody

    public ReturnMessage test(){
        return ReturnMessage.success().add("name","����").add("sex","��");
    }


    //��ȡȫ��רҵ
    @RequestMapping(value="",method=RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getMajor(){
        //��ѯ���ݿ⣬��ȡ��Ҫ����Ϣ
        List<Major> data=majorService.getAll();
        //��list��Ϣadd��ReturnMessage����
        return ReturnMessage.success().add("pageInfo",data);
    }


}
