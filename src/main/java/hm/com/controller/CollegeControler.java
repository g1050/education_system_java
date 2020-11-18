package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.College;
import hm.com.bean.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import hm.com.service.CollegeService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;

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
    @RequestMapping("/all")
    @ResponseBody
    public ReturnMessage getAllCollege(){
        List<College> list = collegeService.getAll();
        return ReturnMessage.success().add("college",list);
    }

    //添加学院
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage saveCollege(@RequestBody  College college){

        //验证
//        if(result.hasErrors()){
//            //显示校验失败信息
//            Map<String, Object> map = new HashMap<String, Object>();
//            List<FieldError> erros = result.getFieldErrors();
//            for(FieldError fieldError:erros){
//                System.out.println("错误字段名 " + fieldError.getField());
//                System.out.println("错误信息" + fieldError.getDefaultMessage());
//                map.put(fieldError.getField(),fieldError.getDefaultMessage());
//            }
//            return ReturnMessage.fail().add("errorFields",erros);
//        }

        int res = collegeService.saveCollege(college);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }

    //更新学院
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateCollege(@RequestBody  College college){
        int res = collegeService.updateCollege(college);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }

    //删除学院 批量单个二合一
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteCollege(@PathVariable("ids")String ids){
        //System.out.println(id);
        if(ids.contains("-")){
            String[] strIds = ids.split("-");
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                delIds.add(Integer.parseInt(string));
            }
            collegeService.deleteColleges(delIds);
            return ReturnMessage.success();
        }else{
            //单个删除
            collegeService.deleteCollege(Integer.parseInt(ids));
            return ReturnMessage.success();
        }

    }
}
