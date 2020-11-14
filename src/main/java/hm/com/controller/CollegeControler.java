package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.College;
import hm.com.bean.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import hm.com.service.CollegeService;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/11/13 11:45
 * @description：学院类控制器
 * @modified By：
 * @version: 0.1$
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/college")
public class CollegeControler {

    @Autowired
    CollegeService collegeService;

    //分页展示部门信息
    @RequestMapping("")
    @ResponseBody
    public ReturnMessage getCollegeByPageAndLimit(@RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "limit",defaultValue = "10")Integer limit){
        //引入pageHelper插件
        PageHelper.startPage(page,limit);
        //查询
        List<College> list = collegeService.getAll();
        //pageInfo包装,第二个参数连续显示的页数
        PageInfo pageInfo = new PageInfo(list,5);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //获取所有的部门信息
    @RequestMapping("/all")
    @ResponseBody
    public ReturnMessage getAllCollege(){
        List<College> list = collegeService.getAll();
        return ReturnMessage.success().add("college",list);
    }

    //添加学院
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage saveCollege(@RequestBody College college){
        System.out.println("收到添加学院请求");
        System.out.println(college.getLocation());
        int res = collegeService.saveCollege(college);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }


}
