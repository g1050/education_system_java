package hm.com.controller;
import hm.com.util.ReturnMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
class StudentController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage test() {
        System.out.println("收到class/test请求/123");
        //1.收到请求，到相应的函数
        //2.查询数据库，获取数据

        //3.把数据发送给前端
        return ReturnMessage.success().add("test","asd").add("pageInfo","asd");
    }


}
