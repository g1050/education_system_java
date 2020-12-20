package hm.com.controller;

import hm.com.bean.Role;
import hm.com.service.RoleService;
import hm.com.util.ReturnMessage;
import hm.com.service.ManagerService;
import hm.com.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：sky
 * @date ：Created in 2020/11/27 20:57
 * @description：登录
 * @modified By：
 * @version: $
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage login(@RequestParam("username")String username,@RequestParam("password")String password){

        Integer oldId = roleService.verify(username,password);
        if(oldId == -1){
            return ReturnMessage.passwordWrong();
        }else{
            String token = JWTUtils.token(oldId,username) ;//role为保留功能，暂时没有设计
            return ReturnMessage.success().add("token",token);
        }

    }

//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    @ResponseBody
//    public  ReturnMessage weibo(HttpServletResponse response){
//        System.out.println("进入weibo");
//
//
//        Cookie cookie = new Cookie("username","123456asd");
//        response.addCookie(cookie);
//        return ReturnMessage.success();
////        return "forward:/api/college";
//    }
}
