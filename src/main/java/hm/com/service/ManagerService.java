package hm.com.service;

import hm.com.bean.Manager;
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
        List<Manager> list = managerMapper.selectByExample(null);
        //2.返回给Controller层
        return list;
    }

    public void deleteManager(Integer id) {
        managerMapper.deleteByPrimaryKey(id);
        return;
    }

    public int updateManager(Manager manager) {
        return managerMapper.updateByPrimaryKey(manager);
    }

//    public List<Manager> getAll() {
//        return managerMapper.selectByExample(null);
//    }

}
