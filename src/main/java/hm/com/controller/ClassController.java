package hm.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ：sky
 * @date ：Created in 2020/11/22 14:21
 * @description：班级控制器
 * @modified By：
 * @version: 0.1$
 */
@Controller
@RequestMapping("/class")
public class ClassController {

    //localhost:8080/api/ class/test
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        System.out.println("收到class/test请求");
        return "收到";
    }

}
