package test;

import hm.com.util.JWTUtils;
import org.junit.Test;
import java.io.UnsupportedEncodingException;
import static hm.com.util.JWTUtils.verify;

/**
 * @author £∫sky
 * @date £∫Created in 2020/12/6 14:43
 * @description£∫≤‚ ‘jwtUtilπ§æﬂ¿‡
 * @modified By£∫
 * @version: $
 */
public class TestJwt {
    @Test
    public  void test( ) throws UnsupportedEncodingException {
        String username ="zhangsan";
        String password = "123";
        String role = "manager";
        Integer old_id = 100;

        String token = JWTUtils.token(old_id,username);
        System.out.println(token);
        String tokenTest = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMyIsImV4cCI6MTYwNjQ4MDczNCwidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.Zbju8j4tXJOtQ7Ifwao1XowNKNpePhXwmZnNd1A6tzM";
//        boolean b = verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd22yZCI6IjEyMyIsImV4cCI6MTU3ODE5NzQxMywidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.IyTZT0tISQQZhGhsNuaqHGV8LD7idjUYjn3MGbulmJg");
        boolean b = verify(token);
        System.out.println(b);
        System.out.println(JWTUtils.getOldId(token));
        System.out.println(JWTUtils.getUsername(token));
    }
}
