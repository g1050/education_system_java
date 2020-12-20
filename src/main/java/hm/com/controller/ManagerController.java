package hm.com.controller;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Manager;
import hm.com.util.Constant;
import hm.com.util.ReturnMessage;
import hm.com.service.ManagerService;
//import net.sf.json.JSONObject;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author ��sky
 * @date ��Created in 2020/11/14 18:14
 * @description������Ա������
 * @modified By��
 * @version: 0.1$
 */
@Controller
@CrossOrigin(origins = "*")
//localhost:8080/api/manager
@RequestMapping(Constant.PREFIX+"/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    //localhost:8080/api /manager/test  GET
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage test(){
        //��ѯ���ݿ⣬��ȡȫ������Ա��Ϣ

        //�ѹ���Ա��Ϣ���ظ�ǰ��

        return ReturnMessage.success().add("name","�ž�").add("����","��Ҫ������").add("pageInfo","PageInfo����");
        //code = 0
        //message = "�ɹ�"
        //extend k:name v:zj
        //k:���� v:...
    }

    //��ӹ���Ա
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage saveManager(@RequestBody Manager manager){
        //���ù���Ա�Ĵ���ʱ��
        manager.setCreateTime(new Date(System.currentTimeMillis()));

        managerService.saveMangager(manager);
        return ReturnMessage.success();
    }

    //��ȡȫ������Ա(��ҳչʾ)
    //localhost:8080/api/manager GET
    //controller -> service -> mapper
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getManager(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "limit",defaultValue = "5")Integer limit){
        //�õ�limit��page
        System.out.println(limit);
        System.out.println(page);

        //����pageHelper���
        PageHelper.startPage(page,limit);

        //��ѯ���ݿ�,��ȡ��Ҫ����Ϣ
        List<Manager> data = managerService.getAll();
        //��lisst��Ϣadd��returnMessage����
        //return ReturnMessage.success().add("pageInfo",data);
        //code = 0 message = "�ɹ�" extend= { PageinfO ����(list)}

        //��װһ������
        PageInfo pageInfo = new PageInfo(data,5);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //ɾ������Ա
    //localhost:8080/api/manager/3 DELETE
    //localhost:8080/api/manager?limit=1&page=1 GET
    //localhost:8080/api/manager/ids
    //localhost:8080/api/manager/3-4-5
    @RequestMapping(value = "{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteManager(@PathVariable("ids")String ids){

        if(ids.contains("-")){//����ɾ��
            //String List
            String[] strIds = ids.split("-");
            //����delIds ���� Integer
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                delIds.add(Integer.parseInt(string));
            }
            //����service��
            managerService.deleteManagers(delIds);
            return ReturnMessage.success();
        }else{//����ɾ��
            //���Ҫɾ���û���id String->Integer
            Integer id = Integer.parseInt(ids);
            managerService.deleteManager(id);
            return ReturnMessage.success();
        }

    }

    //���¹���Ա
    //����ѧԺ
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateCollege(@RequestBody Manager manager){
        int res = managerService.updateManager(manager);
        if(res == 1)
            return ReturnMessage.success();
        else
            return ReturnMessage.fail();
    }

    @RequestMapping(value = "/byusername",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getMangerByUsername(@RequestParam(value = "searchParams")String searchParams,
                                             @RequestParam(value = "page",defaultValue = "")Integer page,
                                             @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);

        String username = map.get("username");
        System.out.println(limit);
        System.out.println(page);

        List<Manager> manager = null;

        if(username.equals("")){//����ȫ������
            manager = managerService.getAll();
        }else{//��ѯ����
            manager = managerService.getMangerByUsername(username);
        }
        //����pageHelper���
        PageHelper.startPage(page,limit);
        //��װһ������
        PageInfo pageInfo = new PageInfo(manager,5);
        System.out.println(username);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }

}
