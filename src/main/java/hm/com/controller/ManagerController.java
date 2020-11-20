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

    @Autowired
    ManagerService managerService;

    //localhost:8080/manager/test  GET
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

    //获取全部管理员(分页展示)
    //localhost:8080/api/manager GET
    //controller -> service -> mapper
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getManager(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "limit",defaultValue = "5")Integer limit){
        //拿到limit和page
        System.out.println(limit);
        System.out.println(page);

        //引入pageHelper插件
        PageHelper.startPage(page,limit);

        //查询数据库,获取需要的信息
        List<Manager> data = managerService.getAll();
        //把lisst信息add到returnMessage后面
        //return ReturnMessage.success().add("pageInfo",data);
        //code = 0 message = "成功" extend= { PageinfO 数组(list)}

        //包装一下数据
        PageInfo pageInfo = new PageInfo(data,5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //获取全部管理员(分页展示)
//    @RequestMapping("")
//    @ResponseBody
//    public ReturnMessage getCollege(@RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "limit",defaultValue = "10")Integer limit){
//        //引入pageHelper插件
//        PageHelper.startPage(page,limit);
//        //查询
//        List<Manager> list = managerService.getAll();
//        //pageInfo包装,第二个参数连续显示的页数
//        PageInfo pageInfo = new PageInfo(list,5);
//        return  ReturnMessage.success().add("pageInfo",pageInfo);
//    }

}
