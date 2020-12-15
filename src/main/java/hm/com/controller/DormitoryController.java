package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Dormitory;
import hm.com.util.ReturnMessage;
import hm.com.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 余龙
 * @version 1.0
 * @date 2020/11/20 23:40
 */

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/dormitory")
public class DormitoryController {

    @Autowired
    DormitoryService dormitoryService;

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//
//    public ReturnMessage test() {
//        return ReturnMessage.success().add("模式", "测试").add("sex", "男");
//    }

    //获取宿舍分页模块
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getDormitory(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        System.out.println(limit);
        System.out.println(page);

        PageHelper.startPage(page, limit);
        //查询数据库
        List<Dormitory> list = dormitoryService.getAll();
        //把list信息add到ReturnMessage后面
        PageInfo pageInfo = new PageInfo(list, 5);
        return ReturnMessage.success().add("pageInfo", pageInfo);
    }
    //获取宿舍所有信息
    @RequestMapping(value = "/all")
    @ResponseBody
    public ReturnMessage getAllDormitory(){
        List<Dormitory> list = dormitoryService.getAll();
        return ReturnMessage.success().add("dormitory",list);
    }
    //@RequestBoby 注解 ->解析json
    //向数据库添加数据
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addDormitory(@RequestBody Dormitory dormitory) {
        //收到json数据
        //解析成class对象

        //利用service层插入数据
        dormitoryService.addDormitory(dormitory);
        return ReturnMessage.success();
    }

    //更新dormitory
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateDormitory(@RequestBody Dormitory dormitory){
        int res = dormitoryService.updateDormitory(dormitory);
        if(res == 1){
            return  ReturnMessage.success();
        }else {
            return  ReturnMessage.fail();
        }
    }

    //删除宿舍 批量单个二合一
    //单个 前端发送请求localhost:8080/api/college/+删除的id号
    //多个 前端发送请求localhost:8080/api/college/+删除的“01-02-03-06”字符串
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteDormitory(@PathVariable("ids")String ids){
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
            dormitoryService.deleteDormitory(delIds);
            return ReturnMessage.success();
        }else{
            //单个删除
            dormitoryService.deleteDormitory(Integer.parseInt(ids));
            return ReturnMessage.success();
        }
    }


}
