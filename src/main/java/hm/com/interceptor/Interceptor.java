package hm.com.interceptor;

import hm.com.util.Constant;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ��sky
 * @date ��Created in 2020/12/20 10:06
 * @description������������Ȩ����
 * @modified By��
 * @version: $
 */
public class Interceptor implements HandlerInterceptor {
    @Override
    //return true���� ,return false������
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //��¼�������
        if(request.getRequestURI().contains("login")){
            return true;
        }

        //��ȡtoken
        String token = request.getHeader(Constant.TOKEN);
        System.out.println(token);
        Boolean b = true;
        //����,����Json
        if(!b){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            JSONObject res = new JSONObject();
            res.put("code", 200);
            res.put("message", "δ��¼");
            out = response.getWriter();
            out.append(res.toString());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
