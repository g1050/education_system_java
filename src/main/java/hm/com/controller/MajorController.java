package hm.com.controller;


import hm.com.bean.ReturnMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/major")
public class MajorController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody

    public ReturnMessage test(){
        return ReturnMessage.success().add("name","ÕÅÈý").add("sex","ÄÐ");
    }


}
