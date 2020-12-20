package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.College;
import hm.com.util.Constant;
import hm.com.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import hm.com.service.CollegeService;

import java.util.ArrayList;
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
@RequestMapping(Constant.PREFIX+"/college")
public class CollegeControler {

    @Autowired
    CollegeService collegeService;

    //分页展示部门信息
    //localhost:8080/api/college?page=1&limit=5
    //默认method是get
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getCollege(@RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "limit",defaultValue = "10")Integer limit){
        //引入pageHelper插件
        PageHelper.startPage(page,limit);
        //查询
        List<College> list = collegeService.getAll();
        //pageInfo包装,第二个参数连续显示的页数
        PageInfo pageInfo = new PageInfo(list,5);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //获取所有的部门信息
    //localhost:8080/api/ college/all
    @RequestMapping("/all")
    @ResponseBody
    public ReturnMessage getAllCollege(){
        List<College> list = collegeService.getAll();
        return ReturnMessage.success().add("college",list);
    }

    //添加学院
    //post方法
    //url-> controller -> service(自己实现) -> dao
    //localhost:8080/api/college POST
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage saveCollege(@RequestBody  College college){

        collegeService.saveCollege(college);
        return ReturnMessage.success();
    }

    //更新学院
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateCollege(@RequestBody  College college
//            ,@RequestHeader(value = "access_token")String token
    ){
//        System.out.println("token = " + token);
        int res = collegeService.updateCollege(college);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }

    //删除学院 批量单个二合一
    //单个 localhost:8080/api/college/27
    //多个 localhost:8080/api/college/27-26-21-23
    @RequestMapping(value = "/{ids} ",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteCollege(@PathVariable("ids")String ids){
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
            collegeService.deleteColleges(delIds);
            return ReturnMessage.success();
        }else{
            //单个删除
            collegeService.deleteCollege(Integer.parseInt(ids));
            return ReturnMessage.success();
        }

    }
}
