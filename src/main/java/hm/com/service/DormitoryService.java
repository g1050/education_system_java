package hm.com.service;

import hm.com.bean.Dormitory;
import hm.com.bean.DormitoryExample;
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

    //service层
    public List<Dormitory> getAll() {
        //1.从数据库查询数据
        List<Dormitory> list = dormitoryMapper.selectByExample(null);
        //2.返回给Controller层
        return list;
    }

    //调用dao层方法实现数据插入数据库
    public void addDormitory(Dormitory dormitory) {
        dormitoryMapper.insertSelective(dormitory);
        return;
    }
    //更新数据库
    public int updateDormitory(Dormitory dormitory) {
        return dormitoryMapper.updateByPrimaryKey(dormitory);
    }

    //删除数据库单个多个宿舍信息
    public int deleteDormitory(Integer id) {
        return dormitoryMapper.deleteByPrimaryKey(id);
}

    public int deleteDormitory(List<Integer> ids){
        DormitoryExample example = new DormitoryExample();
        DormitoryExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return dormitoryMapper.deleteByExample(example);
    }
}
