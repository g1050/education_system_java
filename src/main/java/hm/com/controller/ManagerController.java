package hm.com.controller;

import hm.com.bean.Manager;
import hm.com.bean.ReturnMessage;
import hm.com.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ：sky
 * @date ：Created in 2020/11/14 18:14
 * @description：管理员控制器
 * @modified By：
 * @version: 0.1$
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/manager")
public class ManagerController {

//    @Autowired
//    ManagerService managerService;

    //添加管理员
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ReturnMessage saveManager(Manager manager){
        //managerService.saveMangager(manager);
        return null;
    }
}
