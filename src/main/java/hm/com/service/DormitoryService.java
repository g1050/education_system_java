package hm.com.service;

import hm.com.bean.Dormitory;
import hm.com.dao.DormitoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 余龙
 * @version 1.0
 * @date 2020/11/21 0:05
 */

@Service
public class DormitoryService {
    @Autowired
    DormitoryMapper dormitoryMapper;

    public void saveMangager(Dormitory dormitory) {
        dormitoryMapper.insertSelective(dormitory);
    }

    //service层
    public List<Dormitory> getAll() {
        //1.从数据库查询数据
        List<Dormitory> list = dormitoryMapper.selectByExample(null);
        //2.返回给Controller层
        return list;
    }
}
