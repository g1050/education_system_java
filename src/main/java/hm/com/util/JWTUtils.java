package hm.com.util;

/**
 * @author ：sky
 * @date ：Created in 2020/11/27 18:03
 * @description：jwt工具类
 * @modified By：
 * @version: $
 */
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc   使用token验证用户是否登录
 * @author zm
 **/
public class JWTUtils {
    //设置过期时间
//    private static final long EXPIRE_DATE=30*60*10000; //50h
    private static final long EXPIRE_DATE= 60*1000; // 1min

    //token秘钥
    private static final String TOKEN_SECRET = "ZCfasfhuaUUHufguGuwu2020BQWE";

    public static String token (String username){

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username",username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return token;
    }

    /**
     * @desc   验证token，通过返回true
     * @params [token]需要校验的串
     **/
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    public static String getUsername(String token) throws UnsupportedEncodingException {
            DecodedJWT verifier = null;
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            try {
                verifier = JWT.require(algorithm).build().verify(token);
            } catch (Exception e) {
                System.out.println("get username error");
                //JSONObject jsonObject = new JSONObject();
                //jsonObject.put("status", "401");
                //jsonObject.put("msg", "验证失败，请重新登录!");
                // TODO: 处理验证异常
            }
            assert verifier != null;
            return verifier.getClaim("username").asString();
    }

    @Test
    public  void test( ) throws UnsupportedEncodingException {
        String username ="zhangsan";
        String password = "123";
        String token = token(username);
        System.out.println(token);
        String tokenTest = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMyIsImV4cCI6MTYwNjQ4MDczNCwidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.Zbju8j4tXJOtQ7Ifwao1XowNKNpePhXwmZnNd1A6tzM";
//        boolean b = verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd22yZCI6IjEyMyIsImV4cCI6MTU3ODE5NzQxMywidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.IyTZT0tISQQZhGhsNuaqHGV8LD7idjUYjn3MGbulmJg");
        boolean b = verify(token);
        System.out.println(b);
        System.out.println(getUsername(token));
    }
}
