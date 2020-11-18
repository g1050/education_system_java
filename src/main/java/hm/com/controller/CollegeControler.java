package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.College;
import hm.com.bean.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import hm.com.service.CollegeService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;

/**
 * @author ��sky
 * @date ��Created in 2020/11/13 11:45
 * @description��ѧԺ�������
 * @modified By��
 * @version: 0.1$
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/college")
public class CollegeControler {

    @Autowired
    CollegeService collegeService;

    //��ҳչʾ������Ϣ
    @RequestMapping("")
    @ResponseBody
    public ReturnMessage getCollege(@RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "limit",defaultValue = "10")Integer limit){
        //����pageHelper���
        PageHelper.startPage(page,limit);
        //��ѯ
        List<College> list = collegeService.getAll();
        //pageInfo��װ,�ڶ�������������ʾ��ҳ��
        PageInfo pageInfo = new PageInfo(list,5);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //��ȡ���еĲ�����Ϣ
    @RequestMapping("/all")
    @ResponseBody
    public ReturnMessage getAllCollege(){
        List<College> list = collegeService.getAll();
        return ReturnMessage.success().add("college",list);
    }

    //���ѧԺ
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage saveCollege(@RequestBody  College college){

        //��֤
//        if(result.hasErrors()){
//            //��ʾУ��ʧ����Ϣ
//            Map<String, Object> map = new HashMap<String, Object>();
//            List<FieldError> erros = result.getFieldErrors();
//            for(FieldError fieldError:erros){
//                System.out.println("�����ֶ��� " + fieldError.getField());
//                System.out.println("������Ϣ" + fieldError.getDefaultMessage());
//                map.put(fieldError.getField(),fieldError.getDefaultMessage());
//            }
//            return ReturnMessage.fail().add("errorFields",erros);
//        }

        int res = collegeService.saveCollege(college);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }

    //����ѧԺ
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateCollege(@RequestBody  College college){
        int res = collegeService.updateCollege(college);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }

    //ɾ��ѧԺ ������������һ
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteCollege(@PathVariable("ids")String ids){
        //System.out.println(id);
        if(ids.contains("-")){
            String[] strIds = ids.split("-");
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                delIds.add(Integer.parseInt(string));
            }
            collegeService.deleteColleges(delIds);
            return ReturnMessage.success();
        }else{
            //����ɾ��
            collegeService.deleteCollege(Integer.parseInt(ids));
            return ReturnMessage.success();
        }

    }
}
