package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Club;
import hm.com.service.ClubService;
import hm.com.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ����
 * @version 1.0
 * @date 2020/12/11 11:39
 */

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/club")
public class ClubController {
    @Autowired
    ClubService clubService;
    //��ȡ���ŷ�ҳģ��
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getClub(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        System.out.println(limit);
        System.out.println(page);

        PageHelper.startPage(page, limit);
        //��ѯ���ݿ�
        List<Club> list = clubService.getAll();
        //��list��Ϣadd��ReturnMessage����
        PageInfo pageInfo = new PageInfo(list, 5);
        return ReturnMessage.success().add("pageInfo", pageInfo);
    }
    //��ȡ����������Ϣ
    @RequestMapping(value = "/all")
    @ResponseBody
    public ReturnMessage getAllClub(){
        List<Club> list = clubService.getAll();
        return ReturnMessage.success().add("club",list);
    }
    //@RequestBoby ע�� ->����json
    //�����ݿ��������
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addClub(@RequestBody Club club) {
        //�յ�json����
        //������class����

        //����service���������
        clubService.addClub(club);
        return ReturnMessage.success();
    }

    //����club
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnMessage updateClub(@RequestBody Club club){
        int res = clubService.updateClub(club);
        if(res == 1){
            return  ReturnMessage.success();
        }else {
            return  ReturnMessage.fail();
        }
    }

    //ɾ��club ������������һ
    //���� ǰ�˷�������localhost:8080/api/college/+ɾ����id��
    //��� ǰ�˷�������localhost:8080/api/college/+ɾ���ġ�01-02-03-06���ַ���
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteClub(@PathVariable("ids")String ids){
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
            clubService.deleteClub(delIds);
            return ReturnMessage.success();
        }else{
            //����ɾ��
            clubService.deleteClub(Integer.parseInt(ids));
            return ReturnMessage.success();
        }
    }
}
