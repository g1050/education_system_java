package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.College;
import hm.com.util.Constant;
import hm.com.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import hm.com.service.CollegeService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ��sky
 * @date ��Created in 2020/11/13 11:45
 * @description��ѧԺ�������
 * @modified By��
 * @version: 0.1$
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(Constant.PREFIX+"/college")
public class CollegeControler {

    @Autowired
    CollegeService collegeService;

    //��ҳչʾ������Ϣ
    //localhost:8080/api/college?page=1&limit=5
    //Ĭ��method��get
    @RequestMapping(value = "",method = RequestMethod.GET)
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
    //localhost:8080/api/ college/all
    @RequestMapping("/all")
    @ResponseBody
    public ReturnMessage getAllCollege(){
        List<College> list = collegeService.getAll();
        return ReturnMessage.success().add("college",list);
    }

    //���ѧԺ
    //post����
    //url-> controller -> service(�Լ�ʵ��) -> dao
    //localhost:8080/api/college POST
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage saveCollege(@RequestBody  College college){

        collegeService.saveCollege(college);
        return ReturnMessage.success();
    }

    //����ѧԺ
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateCollege(@RequestBody  College college
//            ,@RequestHeader(value = "access_token")String token
    ){
//        System.out.println("token = " + token);
        int res = collegeService.updateCollege(college);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }

    //ɾ��ѧԺ ������������һ
    //���� localhost:8080/api/college/27
    //��� localhost:8080/api/college/27-26-21-23
    @RequestMapping(value = "/{ids} ",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteCollege(@PathVariable("ids")String ids){
        //System.out.println(id);
        //if else�жϵ���ɾ�����߶��ɾ��
        if(ids.contains("-")){
            //String List
            String[] strIds = ids.split("-");
            //����delIds ���� Integer
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                delIds.add(Integer.parseInt(string));
            }
            //����service��
            collegeService.deleteColleges(delIds);
            return ReturnMessage.success();
        }else{
            //����ɾ��
            collegeService.deleteCollege(Integer.parseInt(ids));
            return ReturnMessage.success();
        }

    }
}
