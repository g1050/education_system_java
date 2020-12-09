package hm.com.controller;

import hm.com.util.ReturnMessage;
import hm.com.service.ManagerService;
import hm.com.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author £ºsky
 * @date £ºCreated in 2020/11/27 20:57
 * @description£ºµÇÂ¼
 * @modified By£º
 * @version: $
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage login(@RequestParam("username")String username,@RequestParam("password")String password){

        System.out.println(username);
        System.out.println(password);
        if(!managerService.verify(username,password)){
            return ReturnMessage.fail();
        }
        String token = JWTUtils.token(username);

        return ReturnMessage.success().add("token",token);
    }
}
