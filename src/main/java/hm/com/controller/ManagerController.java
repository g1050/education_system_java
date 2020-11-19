package hm.com.controller;

import hm.com.bean.Manager;
import hm.com.bean.ReturnMessage;
import hm.com.service.ManagerService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

//    @Autowired
//    ManagerService managerService;

    //localhost:8080/manager/test get
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
}
