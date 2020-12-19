package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Club;
import hm.com.service.ClubService;
import hm.com.util.ReturnMessage;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ????
 * @version 1.0
 * @date 2020/12/11 11:39
 */

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/club")
public class ClubController {
    @Autowired
    ClubService clubService;
    //????????????
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getClub(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        System.out.println(limit);
        System.out.println(page);

        PageHelper.startPage(page, limit);
        //????????
        List<Club> list = clubService.getAll();
        //??list???add??ReturnMessage????
        PageInfo pageInfo = new PageInfo(list, 5);
        return ReturnMessage.success().add("pageInfo", pageInfo);
    }
    //??????????????
    @RequestMapping(value = "/all")
    @ResponseBody
    public ReturnMessage getAllClub(){
        List<Club> list = clubService.getAll();
        return ReturnMessage.success().add("club",list);
    }
    //@RequestBoby ??? ->????json
    //??????????????
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addClub(@RequestBody Club club) {
        //???json????
        //??????class????

        //????service?????????
        clubService.addClub(club);
        return ReturnMessage.success();
    }

    //????club
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

    //???club ?????????????
    //???? ??????????localhost:8080/api/college/+?????id??
    //??? ??????????localhost:8080/api/college/+??????01-02-03-06???????
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteClub(@PathVariable("ids")String ids){
        //if else?§Ø????????????????
        if(ids.contains("-")){
            //String List
            String[] strIds = ids.split("-");
            //????delIds ???? Integer
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                delIds.add(Integer.parseInt(string));
            }
            //????service??
            clubService.deleteClub(delIds);
            return ReturnMessage.success();
        }else{
            //???????
            clubService.deleteClub(Integer.parseInt(ids));
            return ReturnMessage.success();
        }
    }

    //???
    @RequestMapping(value = "/bycollege",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getClubByCollege(@RequestParam(value = "searchParams")String searchParams,
                                             @RequestParam(value = "page",defaultValue = "")Integer page,
                                             @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);

        String college = map.get("college");
        System.out.println(limit);
        System.out.println(page);

        List<Club> club = null;

        if(college.equals("")){//???????????
            club = clubService.getAll();
        }else{//???????
            club = clubService.getClubByCollege(college);
        }
        //????pageHelper???
        PageHelper.startPage(page,limit);
        //??????????
        PageInfo pageInfo = new PageInfo(club,5);
        System.out.println(college);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }
}
