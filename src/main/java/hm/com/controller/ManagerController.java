package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Manager;
import hm.com.bean.ReturnMessage;
import hm.com.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ��sky
 * @date ��Created in 2020/11/14 18:14
 * @description������Ա������
 * @modified By��
 * @version: 0.1$
 */
@Controller
@CrossOrigin(origins = "*")
//localhost:8080/api/manager
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    //localhost:8080/manager/test  GET
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage test(){
        //��ѯ���ݿ⣬��ȡȫ������Ա��Ϣ

        //�ѹ���Ա��Ϣ���ظ�ǰ��

        return ReturnMessage.success().add("name","�ž�").add("����","��Ҫ������").add("pageInfo","PageInfo����");
        //code = 0
        //message = "�ɹ�"
        //extend k:name v:zj
        //k:���� v:...
    }

    //��ӹ���Ա
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ReturnMessage saveManager(Manager manager){
        //managerService.saveMangager(manager);
        return null;
    }

    //��ȡȫ������Ա(��ҳչʾ)
    //localhost:8080/api/manager GET
    //controller -> service -> mapper
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getManager(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "limit",defaultValue = "5")Integer limit){
        //�õ�limit��page
        System.out.println(limit);
        System.out.println(page);

        //����pageHelper���
        PageHelper.startPage(page,limit);

        //��ѯ���ݿ�,��ȡ��Ҫ����Ϣ
        List<Manager> data = managerService.getAll();
        //��lisst��Ϣadd��returnMessage����
        //return ReturnMessage.success().add("pageInfo",data);
        //code = 0 message = "�ɹ�" extend= { PageinfO ����(list)}

        //��װһ������
        PageInfo pageInfo = new PageInfo(data,5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //��ȡȫ������Ա(��ҳչʾ)
//    @RequestMapping("")
//    @ResponseBody
//    public ReturnMessage getCollege(@RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "limit",defaultValue = "10")Integer limit){
//        //����pageHelper���
//        PageHelper.startPage(page,limit);
//        //��ѯ
//        List<Manager> list = managerService.getAll();
//        //pageInfo��װ,�ڶ�������������ʾ��ҳ��
//        PageInfo pageInfo = new PageInfo(list,5);
//        return  ReturnMessage.success().add("pageInfo",pageInfo);
//    }

}
