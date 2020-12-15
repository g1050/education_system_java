package hm.com.service;

import hm.com.bean.ClassExample;
import hm.com.bean.CollegeExample;
import hm.com.bean.Manager;
import hm.com.bean.ManagerExample;
import hm.com.dao.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/11/14 18:18
 * @description：管理员服务类
 * @modified By：
 * @version: 0.1$
 */
//service -> mapper
@Service
public class ManagerService {
    @Autowired
    ManagerMapper managerMapper;

    public void saveMangager(Manager manager) {
        managerMapper.insertSelective(manager);
    }

    //service层
    public List<Manager> getAll() {
        //1.从数据库查询数据
        List<Manager> list = managerMapper.selectByExampleWithCollege(null);
        //2.返回给Controller层
        return list;
    }

    public void deleteManager(Integer id) {
        managerMapper.deleteByPrimaryKey(id);
        return;
    }

    public int updateManager(Manager manager) {
        return managerMapper.updateByPrimaryKeySelective(manager);
    }

    public Boolean verify(String username, String password) {
        ManagerExample managerExample = new ManagerExample();

        ManagerExample.Criteria criteria1 = managerExample.createCriteria().andUsernameEqualTo(username);
        List<Manager> managers = managerMapper.selectByExample(managerExample);

        if(managers.size() == 0){
            return false;
        }

        return  managers.get(0).getPassword().equals(password);
    }

    public void deleteManagers(List<Integer> delIds) {
        ManagerExample example = new ManagerExample();
        ManagerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(delIds);

        managerMapper.deleteByExample(example);
        return;
    }

    public Manager getMangerByUsername(String username) {
        List<Manager> all = getAll();
        for(Manager manager : all){
            if(manager.getUsername().equals(username)){
                return manager;
            }
        }
        return null;
    }

//    public List<Manager> getAll() {
//        return managerMapper.selectByExample(null);
//    }

}
