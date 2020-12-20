package hm.com.util;

/**
 * @author ：sky
 * @date ：Created in 2020/12/20 17:41
 * @description：常量
 * @modified By：
 * @version: $
 */
public class Constant {
    public final static String TOKEN = "access_token";
    public final static String PREFIX = "/api";
    public final static String OLDID = "old_id";
    //设置过期时间
    //private static final long EXPIRE_DATE=30*60*10000; //50h
    public static final long EXPIRE_DATE= 60*1000*60; // 60min
}
