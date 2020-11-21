package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Dormitory;
import hm.com.bean.ReturnMessage;
import hm.com.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody

    public ReturnMessage test() {
        return ReturnMessage.success().add("模式", "测试").add("sex", "男");
    }

    //获取宿舍信息
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
}
