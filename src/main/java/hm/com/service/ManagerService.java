package hm.com.service;

import hm.com.bean.Manager;
import hm.com.dao.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ��sky
 * @date ��Created in 2020/11/14 18:18
 * @description������Ա������
 * @modified By��
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

    //service��
    public List<Manager> getAll() {
        //1.�����ݿ��ѯ����
        List<Manager> list = managerMapper.selectByExample(null);
        //2.���ظ�Controller��
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
