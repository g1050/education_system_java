package hm.com.service;

import hm.com.bean.Major;
import hm.com.dao.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    MajorMapper majorMapper;
    public void saveMajor(Major major) {
        majorMapper.insertSelective(major);
    }

    public List<Major> getAll() {
        //从数据库查询数据
        List<Major> list= majorMapper.selectByExample(null);
        //返回给controller
        return list;
    }


}
