package hm.com.interceptor;

import hm.com.util.Constant;
import hm.com.util.JWTUtils;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ：sky
 * @date ：Created in 2020/12/20 10:06
 * @description：拦截器，鉴权操作
 * @modified By：
 * @version: $
 */
public class Interceptor implements HandlerInterceptor {
    @Override
    //return true放行 ,return false不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录请求放行
        if(request.getRequestURI().contains("login")){
            return true;
        }
        //放行OPTIONS请求
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        //获取token
        String token = request.getHeader(Constant.TOKEN);
        System.out.println("token" + token);
        if(token == null){
            System.out.println("token is null");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control","no-cache");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            JSONObject res = new JSONObject();
            res.put("code", 200);
            res.put("message", "请求没有token");
            out = response.getWriter();
            out.append(res.toString());
            return false;
        }
        Boolean b = JWTUtils.verify(token);
        //拦截,返回Json
        if(!b){
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control","no-cache");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            JSONObject res = new JSONObject();
            res.put("code", 200);
            res.put("message", "未登录");
            out = response.getWriter();
            out.append(res.toString());
            return false;
        }
        request.setAttribute(Constant.OLDID,JWTUtils.getOldId(token));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
