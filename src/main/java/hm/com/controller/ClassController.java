package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Class;
import hm.com.bean.Dormitory;
import hm.com.bean.Manager;
import hm.com.bean.Teacher;
import hm.com.util.Constant;
import hm.com.util.ReturnMessage;
import hm.com.service.ClassService;
//import net.sf.json.JSONObject;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：sky
 * @date ：Created in 2020/11/22 14:21
 * @description：班级控制器
 * @modified By：
 * @version: 0.1$
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(Constant.PREFIX+"/class")
public class ClassController {

    @Autowired
    ClassService classService;

    //获取class分页模块
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

    //获取班级所有信息
    @RequestMapping(value = "/all")
    @ResponseBody
    public ReturnMessage getAllCollegeAndMajor(){
        List<Class> list = classService.getAll();
        return ReturnMessage.success().add("class",list);
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

    //根据学院查询
    @RequestMapping(value = "/bycollege",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getClassByCollege(@RequestParam(value = "college",defaultValue = "")String college ){
        List<Class>list = null;
        if(college.equals("")){//返回全部数据

        }else{//查询返回
             list = classService.getClassByCollege(college);
        }
        System.out.println(college);
        return  ReturnMessage.success().add("pageInfo",list);
    }
    //根据名称查询
    @RequestMapping(value = "/byname",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getClassByName(@RequestParam(value = "searchParams")String searchParams,
                                        @RequestParam(value = "page",defaultValue = "")Integer page,
                                        @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);

        String name = map.get("name");
        System.out.println("page");
        System.out.println("limit");

        List<Class> aclass = null;
        if(name.equals("")){//返回全部数据
            aclass = classService.getAll();
        }else{//查询返回
            aclass = classService.getClassByName(name);
        }

        //引入pageHelper插件
        PageHelper.startPage(page,limit);
        //包装一下数据
        PageInfo pageInfo = new PageInfo(aclass,5);
        System.out.println(name);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //多条件查询
    @RequestMapping(value = "/bymore",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getTeacherByMore(@RequestParam(value = "searchParams")String searchParams,
                                          @RequestParam(value = "page",defaultValue = "")Integer page,
                                          @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);
        String college = map.get("college");
        String className = map.get("name");
        System.out.println("page");
        System.out.println("limit");

        List<Class> classes = null;
        if(className.equals("")&&college.equals("")){
            classes = classService.getAll();
        }else if(className.equals("")){
            classes = classService.getClassByCollege(college);
        }else if(college.equals("")){
            classes = classService.getClassByName(className);
        }else{
            classes = classService.getClassByMore(college,className);
        }

        PageHelper.startPage(page,limit);

        PageInfo pageInfo = new PageInfo(classes,5);

        return ReturnMessage.success().add("pageInfo",pageInfo);
    }
}
