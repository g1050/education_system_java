package hm.com.service;

import hm.com.bean.Manager;
import hm.com.dao.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：sky
 * @date ：Created in 2020/11/14 18:18
 * @description：管理员服务类
 * @modified By：
 * @version: 0.1$
 */
public class ManagerService {
    @Autowired
    ManagerMapper managerMapper;

    public void saveMangager(Manager manager) {
        managerMapper.insertSelective(manager);
    }
}
