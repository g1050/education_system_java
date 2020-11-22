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
        return ReturnMessage.success().add("name","张三").add("sex","男");
    }


    //获取全部专业
    @RequestMapping(value="",method=RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getMajor(@RequestParam(value="page",defaultValue = "1")Integer page,@RequestParam(value="limit",defaultValue = "5")Integer limit
//    ,@RequestParam(value = "name",defaultValue = "sky")String name
    ){
        System.out.println(limit);
        System.out.println(page);
//        System.out.println(name);

        PageHelper.startPage(page,limit);
        //查询数据库，获取需要的信息
        List<Major> data=majorService.getAll();
        //把list信息add到ReturnMessage后面
        PageInfo pageInfo=new PageInfo(data,5);
        return ReturnMessage.success().add("pageInfo",pageInfo);

    }


}
