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
        //�����ݿ��ѯ����
        List<Major> list= majorMapper.selectByExample(null);
        //���ظ�controller
        return list;
    }


}
