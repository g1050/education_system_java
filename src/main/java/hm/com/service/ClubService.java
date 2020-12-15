package hm.com.service;

import hm.com.bean.Club;
import hm.com.bean.ClubExample;
import hm.com.bean.Manager;
import hm.com.dao.ClubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 余龙
 * @version 1.0
 * @date 2020/12/11 11:40
 */

@Service
public class ClubService {
    @Autowired
    ClubMapper clubMapper;

    //service层
    public List<Club> getAll() {
        //1.从数据库查询数据
        List<Club> list = clubMapper.selectByExampleWithCollege(null);
        //2.返回给Controller层
        return list;
    }

    //调用dao层方法实现数据插入数据库
    public void addClub(Club club) {
        clubMapper.insertSelective(club);
        return;
    }
    //更新数据库
    public int updateClub(Club club) {
        return clubMapper.updateByPrimaryKeySelective(club);
    }


    //删除数据库单个多个club信息
    public int deleteClub(Integer id) {
        return clubMapper.deleteByPrimaryKey(id);
    }

    public int deleteClub(List<Integer> ids){
        ClubExample example = new ClubExample();
        ClubExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return clubMapper.deleteByExample(example);
    }
}
