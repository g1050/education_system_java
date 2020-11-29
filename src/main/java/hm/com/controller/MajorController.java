package hm.com.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Major;
import hm.com.bean.ReturnMessage;
import hm.com.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ReturnMessage getMajor(@RequestParam(value="page",defaultValue = "1")Integer page,@RequestParam(value="limit",defaultValue = "5")Integer limit
//    ,@RequestParam(value = "name",defaultValue = "sky")String name
    ){
        System.out.println(limit);
        System.out.println(page);
//        System.out.println(name);

        PageHelper.startPage(page,limit);
        //��ѯ���ݿ⣬��ȡ��Ҫ����Ϣ
        List<Major> data=majorService.getAll();
        //��list��Ϣadd��ReturnMessage����
        PageInfo pageInfo=new PageInfo(data,5);
        return ReturnMessage.success().add("pageInfo",pageInfo);

    }

    //���רҵ
    @RequestMapping(value = "",method=RequestMethod.POST)
    @ResponseBody
    public ReturnMessage saveMajor(@RequestBody Major major){
        //�յ�json����
        //������class����
        //����service���������
        majorService.saveMajor(major);
        return ReturnMessage.success();
    }

    //ɾ��רҵ
    @RequestMapping(value = "{ids}",method=RequestMethod.DELETE)
    @ResponseBody

    public ReturnMessage deleteMajor(@PathVariable("ids")String ids){
        //���Ҫɾ�����û�id
        Integer id=Integer.parseInt(ids);
        majorService.deleteMajor(id);
        return ReturnMessage.success();
    }


}
