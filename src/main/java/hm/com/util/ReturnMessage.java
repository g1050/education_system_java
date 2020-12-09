package hm.com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ��sky
 * @date ��Created in 2020/11/14 13:03
 * @description��ͨ�÷����࣬����ͨ�õ�json����
 * @modified By��
 * @version: 0.1$
 */
public class ReturnMessage {
    //0�ɹ� 100ʧ��
    //200 tokenʧЧ����Ч
    private int code;
    private String Message;
    //key:"test" value:123
    private Map<String,Object> extend = new HashMap<String,Object>();

    public static ReturnMessage success(){
        ReturnMessage result = new ReturnMessage();
        result.setCode(0);
        result.setMessage("�ɹ�");
        return result;
    }

    public static ReturnMessage invalidToken(){
        ReturnMessage result = new ReturnMessage();
        result.setCode(200);
        result.setMessage("���µ�¼");
        return result;
    }

    public static ReturnMessage fail(){
        ReturnMessage result = new ReturnMessage();
        result.setCode(100);
        result.setMessage("ʧ��");
        return result;
    }


    public ReturnMessage add(String key,Object value){
        this.getExtend().put(key,value);
        return this;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
