package hm.com.controller;
import hm.com.util.ReturnMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
class StudentController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage test() {
        System.out.println("�յ�class/test����/123");
        //1.�յ����󣬵���Ӧ�ĺ���
        //2.��ѯ���ݿ⣬��ȡ����

        //3.�����ݷ��͸�ǰ��
        return ReturnMessage.success().add("test","asd").add("pageInfo","asd");
    }


}
