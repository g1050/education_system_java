package hm.com.interceptor;

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
@RequestMapping(value = "/login")
public class LoginInterceptor {

    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage login(@RequestParam("username")String username,@RequestParam("password")String password){

        if(!managerService.verify(username,password)){
            return ReturnMessage.fail();
        }
        String token = JWTUtils.token(username);

        return ReturnMessage.success().add("token",token);
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
