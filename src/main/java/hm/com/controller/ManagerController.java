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
 * @author ：sky
 * @date ：Created in 2020/11/14 18:14
 * @description：管理员控制器
 * @modified By：
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
        //查询数据库，获取全部管理员信息

        //把管理员信息返回给前端

        return ReturnMessage.success().add("name","张军").add("数据","想要的数据").add("pageInfo","PageInfo对象");
        //code = 0
        //message = "成功"
        //extend k:name v:zj
        //k:数据 v:...
    }

    //添加管理员
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ReturnMessage saveManager(Manager manager){
        //managerService.saveMangager(manager);
        return null;
    }
}
