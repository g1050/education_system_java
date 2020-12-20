package hm.com.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.College;
import hm.com.bean.Major;
import hm.com.util.Constant;
import hm.com.util.ReturnMessage;
import hm.com.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(Constant.PREFIX+"/major")
public class MajorController {

    @Autowired
    MajorService majorService;
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody

    public ReturnMessage test(){
        return ReturnMessage.success().add("name","张三").add("sex","男");
    }


    //获取全部专业
    @RequestMapping("/all")
    @ResponseBody
    public ReturnMessage getAllCollege(){
        List<Major> list =majorService.getAll();
        return ReturnMessage.success().add("major",list);
    }
    //分页展示专业
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

    //添加专业
    @RequestMapping(value = "",method=RequestMethod.POST)
    @ResponseBody
    public ReturnMessage saveMajor(@RequestBody Major major){
        //收到json数据
        //解析成class对象
        //利用service层插入数据
        majorService.saveMajor(major);
        return ReturnMessage.success();
    }

    //更新专业
    @RequestMapping(value = "",method=RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateMajor(@RequestBody Major major){
        int res=majorService.updateMajor(major);
        if(res==1){
            return ReturnMessage.success();
        }
        else return ReturnMessage.fail();


    }

    //删除专业
    @RequestMapping(value = "{ids}",method=RequestMethod.DELETE)
    @ResponseBody

    public ReturnMessage deleteMajor(@PathVariable("ids")String ids){
        //获得要删除的用户id
        if(ids.contains("-")){
            String[] strIds=ids.split("-");
            //构件delIds数组
            List<Integer> delIds=new ArrayList<Integer>();
            for(String string:strIds){
                delIds.add(Integer.parseInt(string));
            }
            //传给service层
            majorService.deleteMajors(delIds);
            return ReturnMessage.success();
        }
        else{ //单个删除
            majorService.deleteMajor(Integer.parseInt(ids));
            return ReturnMessage.success();
        }

    }


}
