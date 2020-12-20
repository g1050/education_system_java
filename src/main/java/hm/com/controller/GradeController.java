package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.College;
import hm.com.bean.Grade;
import hm.com.service.GradeService;
import hm.com.util.Constant;
import hm.com.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author £∫sky
 * @date £∫Created in 2020/12/20 11:54
 * @description£∫≥…º®π‹¿Ì
 * @modified By£∫
 * @version: $
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(Constant.PREFIX+"/grade")
public class GradeController {
    @Autowired
    GradeService gradeService;

    //localhost:8080/api/grade
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage insertGrade(@RequestBody Grade grade){
        gradeService.insert(grade);
        return ReturnMessage.success();
    }

    //localhost:8080/api/grade/bystudent/29
    @RequestMapping(value = "/bystudent/{studentId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getGrade(@PathVariable("studentId")Integer studentId,
                                  @RequestParam(value = "page",defaultValue = "1")Integer page,
                                  @RequestParam(value = "limit",defaultValue = "10")Integer limit){
        List<Grade> list = gradeService.getGrade(studentId);
        PageHelper.startPage(page,limit);
        PageInfo pageInfo = new PageInfo(list,5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }
}
