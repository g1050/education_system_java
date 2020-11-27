package hm.com.util;

/**
 * @author ��sky
 * @date ��Created in 2020/11/27 18:03
 * @description��jwt������
 * @modified By��
 * @version: $
 */
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc   ʹ��token��֤�û��Ƿ��¼
 * @author zm
 **/
public class JWTUtils {
    //���ù���ʱ��
//    private static final long EXPIRE_DATE=30*60*10000; //50h
    private static final long EXPIRE_DATE= 60*1000; // 1min

    //token��Կ
    private static final String TOKEN_SECRET = "ZCfasfhuaUUHufguGuwu2020BQWE";

    public static String token (String username,String password){

        String token = "";
        try {
            //����ʱ��
            Date date = new Date(System.currentTimeMillis()+EXPIRE_DATE);
            //��Կ�������㷨
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //����ͷ����Ϣ
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //Я��username��password��Ϣ������ǩ��
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username",username)
                    .withClaim("password",password).withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return token;
    }

    /**
     * @desc   ��֤token��ͨ������true
     * @params [token]��ҪУ��Ĵ�
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

    @Test
    public  void test( ) {
        String username ="zhangsan";
        String password = "123";
        String token = token(username,password);
        System.out.println(token);
        String tokenTest = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMyIsImV4cCI6MTYwNjQ4MDczNCwidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.Zbju8j4tXJOtQ7Ifwao1XowNKNpePhXwmZnNd1A6tzM";
//        boolean b = verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd22yZCI6IjEyMyIsImV4cCI6MTU3ODE5NzQxMywidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.IyTZT0tISQQZhGhsNuaqHGV8LD7idjUYjn3MGbulmJg");
        boolean b = verify(tokenTest);
        System.out.println(b);
    }
}
