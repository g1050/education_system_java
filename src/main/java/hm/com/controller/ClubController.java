package hm.com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hm.com.bean.Club;
import hm.com.bean.Teacher;
import hm.com.service.ClubService;
import hm.com.util.Constant;
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
 * @author 余龙
 * @version 1.0
 * @date 2020/12/11 11:39
 */

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(Constant.PREFIX+"/club")
public class ClubController {
    @Autowired
    ClubService clubService;
    //分页模块
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage getClub(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        System.out.println(limit);
        System.out.println(page);

        PageHelper.startPage(page, limit);
        //查询数据库
        List<Club> list = clubService.getAll();
        //把list信息add到ReturnMessage后面
        PageInfo pageInfo = new PageInfo(list, 5);
        return ReturnMessage.success().add("pageInfo", pageInfo);
    }
    //获取所有社团信息
    @RequestMapping(value = "/all")
    @ResponseBody
    public ReturnMessage getAllClub(){
        List<Club> list = clubService.getAll();
        return ReturnMessage.success().add("club",list);
    }
    //@RequestBoby 注解 ->解析json
    //向数据库添加数据
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage addClub(@RequestBody Club club) {
        //收到json数据
        //解析成class对象

        //利用service层插入数据
        clubService.addClub(club);
        return ReturnMessage.success();
    }

    //更新club
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

    //删除宿舍 批量单个二合一
    //单个 前端发送请求localhost:8080/api/club/+删除的id号
    //多个 前端发送请求localhost:8080/api/club/+删除的“01-02-03-06”字符串
    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnMessage deleteClub(@PathVariable("ids")String ids){
        //if else判断单个删除或者多个删除
        if(ids.contains("-")){
            //String List
            String[] strIds = ids.split("-");
            //构建delIds 数组 Integer
            List<Integer> delIds = new ArrayList<Integer>();
            for(String string : strIds){
                delIds.add(Integer.parseInt(string));
            }
            //传给service层
            clubService.deleteClub(delIds);
            return ReturnMessage.success();
        }else{
            //单个删除
            clubService.deleteClub(Integer.parseInt(ids));
            return ReturnMessage.success();
        }
    }

    //搜索查询
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

        if(college.equals("")){//返回全部数据
            club = clubService.getAll();
        }else{//只返回所查字段数据
            club = clubService.getClubByCollege(college);
        }
        //引入pageHelper插件
        PageHelper.startPage(page,limit);
        //包装一下数据
        PageInfo pageInfo = new PageInfo(club,5);
        System.out.println(college);
        return  ReturnMessage.success().add("pageInfo",pageInfo);
    }

    //姓名查询
    @RequestMapping(value = "/byname",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getClubByName(@RequestParam(value = "searchParams")String searchParams,
                                          @RequestParam(value = "page",defaultValue = "")Integer page,
                                          @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);
        String clubName = map.get("name");
        System.out.println("page");
        System.out.println("limit");

        List<Club> clubs = null;
        if (clubName.equals("")){
            clubs = clubService.getAll();
        }else{
            clubs = clubService.getClubByName(clubName);
        }
        //引入pageHelper插件
        PageHelper.startPage(page,limit);
        //包装一下数据
        PageInfo pageInfo = new PageInfo(clubs,5);
        System.out.println(clubName);
        return ReturnMessage.success().add("pageInfo",pageInfo);
    }
    //多条件查询
    @RequestMapping(value = "/bymore",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage getTeacherByMore(@RequestParam(value = "searchParams")String searchParams,
                                          @RequestParam(value = "page",defaultValue = "")Integer page,
                                          @RequestParam(value = "limit",defaultValue = "")Integer limit){
        Map<String, String> map = new HashMap<String, String>();
        map = JSONObject.fromObject(searchParams);
        String college = map.get("college");
        String clubName = map.get("name");
        System.out.println("page");
        System.out.println("limit");

        List<Club> clubs = null;
        if(clubName.equals("")&&college.equals("")){
            clubs = clubService.getAll();
        }else if(clubName.equals("")){
            clubs = clubService.getClubByCollege(college);
        }else if(college.equals("")){
            clubs = clubService.getClubByName(clubName);
        }else{
            clubs = clubService.getClubByMore(college,clubName);
        }

        PageHelper.startPage(page,limit);

        PageInfo pageInfo = new PageInfo(clubs,5);

        return ReturnMessage.success().add("pageInfo",pageInfo);
    }
}
