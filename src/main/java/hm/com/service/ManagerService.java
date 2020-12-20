package hm.com.service;

import hm.com.bean.*;
import hm.com.dao.ManagerMapper;
import hm.com.dao.RoleMapper;
import hm.com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    RoleMapper roleMapper;

    public void saveMangager(Manager manager) {
        managerMapper.insertSelective(manager);
        ManagerExample example = new ManagerExample();
        ManagerExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(manager.getUsername()).andCreateTimeEqualTo(manager.getCreateTime());
        List<Manager> managers = managerMapper.selectByExample(example);
        //插入数据到role表
        if(managers.size() > 0){
            Manager managerRt = managers.get(0);
            Role role = new Role();
            role.setOldId(managerRt.getId());
            role.setUsername(managerRt.getUsername());
            role.setPassword(managerRt.getPassword());
            role.setRole(Constant.MANAGER);
            roleMapper.insert(role);
        }

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

    public void deleteManagers(List<Integer> delIds) {
        ManagerExample example = new ManagerExample();
        ManagerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(delIds);

        managerMapper.deleteByExample(example);
        return;
    }

    public List<Manager> getMangerByUsername(String username) {
        List<Manager> all = getAll();
        List<Manager> list = new ArrayList<Manager>();

        for(Manager manager:all){
            if(manager.getUsername().equals(username)){
                list.add(manager);
            }
        }
        return list;
    }

//    public List<Manager> getAll() {
//        return managerMapper.selectByExample(null);
//    }

}
