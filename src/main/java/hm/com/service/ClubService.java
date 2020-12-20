package hm.com.service;

import hm.com.bean.Class;
import hm.com.bean.Club;
import hm.com.bean.ClubExample;
import hm.com.bean.Manager;
import hm.com.dao.ClubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ����
 * @version 1.0
 * @date 2020/12/11 11:40
 */

@Service
public class ClubService {
    @Autowired
    ClubMapper clubMapper;

    //service��
    public List<Club> getAll() {
        //1.�����ݿ��ѯ����
        List<Club> list = clubMapper.selectByExampleWithCollege(null);
        //2.���ظ�Controller��
        return list;
    }

    //����dao�㷽��ʵ�����ݲ������ݿ�
    public void addClub(Club club) {
        clubMapper.insertSelective(club);
        return;
    }
    //�������ݿ�
    public int updateClub(Club club) {
        return clubMapper.updateByPrimaryKeySelective(club);
    }


    //ɾ�����ݿⵥ�����club��Ϣ
    public int deleteClub(Integer id) {
        return clubMapper.deleteByPrimaryKey(id);
    }

    public int deleteClub(List<Integer> ids){
        ClubExample example = new ClubExample();
        ClubExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return clubMapper.deleteByExample(example);
    }
    //ѧԺ��ѯ
    public List<Club> getClubByCollege(String college) {
        List <Club> all = getAll();
        List<Club> list = new ArrayList<Club>();

        for(Club club : all){
            if(club.getCollege().getName().equals(college)){
                list.add(club);
            }
        }
        return list;
    }
    //�������Ʋ�ѯ
    public List<Club> getClubByName(String clubName){
        List<Club> all = getAll();
        List<Club> list = new ArrayList<Club>();

        for (Club club : all){
            if(club.getName().equals(clubName)){
                list.add(club);
            }
        }
        return list;
    }
    //��������ѯ
    public List<Club> getClubByMore(String college, String clubName) {
        List<Club> all = getAll();
        List<Club> list = new ArrayList<Club>();

        for (Club club : all){
            if(club.getCollege().getName().equals(college) && club.getName().equals(clubName)){
                list.add(club);
            }
        }
        return list;
    }
}
