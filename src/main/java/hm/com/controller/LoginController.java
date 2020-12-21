package hm.com.controller;

import hm.com.bean.Role;
import hm.com.service.RoleService;
import hm.com.util.Constant;
import hm.com.util.ReturnMessage;
import hm.com.service.ManagerService;
import hm.com.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author £ºsky
 * @date £ºCreated in 2020/11/27 20:57
 * @description£ºµÇÂ¼
 * @modified By£º
 * @version: $
 */
@Controller
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = Constant.PREFIX+"/login",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage login(@RequestParam("username")String username,@RequestParam("password")String password){

        StringBuilder role = new StringBuilder();
        Integer oldId = roleService.verify(username,password,role);
        System.out.println(role.toString());
        if(oldId == -1){
            return ReturnMessage.passwordWrong();
        }else{
            String token = JWTUtils.token(oldId,role.toString()) ;
            return ReturnMessage.success().add("token",token).add("role",role.toString());
        }

    }

    @RequestMapping(value = Constant.PREFIX+"/hc",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage checkToken(){
        return ReturnMessage.success();
    }

//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    @ResponseBody
//    public  ReturnMessage weibo(HttpServletResponse response){
//        System.out.println("½øÈëweibo");
//
//
//        Cookie cookie = new Cookie("username","123456asd");
//        response.addCookie(cookie);
//        return ReturnMessage.success();
////        return "forward:/api/college";
//    }
}
