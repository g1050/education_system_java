package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Class;
import hm.com.util.ReturnMessage;
import hm.com.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/11/22 14:21
 * @description：班级控制器
 * @modified By：
 * @version: 0.1$
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;

    //获取class
    //localhost:8080/api/class
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    //Url -> Controller -> Service -> Mapper
    public ReturnMessage getClass(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "limit",defaultValue = "100")Integer limit){
        //收到请求

        //从数据库中查询数据
        List<Class> list;
        list = classService.getAll();
        //把数据给前端
        //包装一下数据
        //引入pageHelper插件
        PageHelper.startPage(page,limit);
        PageInfo pageInfo = new PageInfo(list,5);

        return ReturnMessage.success().add("pageInfo",pageInfo);
        //code = 0 message= edtend{test:zy,pageInfo,...}
    }

    //添加class
    //RequestBody注解，自动解析json-> class
    //localhost:8080/api/class POST
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addClass(@RequestBody Class aclass){
        //收到json数据
        //解析成class对象

        //利用service层插入class
        classService.addClass(aclass);
        return ReturnMessage.success();
    }

    //删除class
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteClass(@PathVariable("ids")String ids){
        //System.out.println(id);
        //if else判断单个删除或者多个删除
        if(ids.contains("-")){
            //String List
            String[] strIds = ids.split("-");
            //构建delIds 数组 Integer
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                delIds.add(Integer.parseInt(string));
            }
            //传给service层
            classService.deleteClasses(delIds);
            return ReturnMessage.success();
        }else{
            //单个删除
            classService.deleteClass(Integer.parseInt(ids));
            return ReturnMessage.success();
        }

    }

    //更新class
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateClass(@RequestBody  Class classa){
        int res = classService.updateClass(classa);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }
}
