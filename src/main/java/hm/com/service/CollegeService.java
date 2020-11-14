package hm.com.service;

import hm.com.bean.College;
import hm.com.dao.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ��sky
 * @date ��Created in 2020/11/13 11:48
 * @description��
 * @modified By��
 * @version: $
 */
@Service
public class CollegeService {

    @Autowired
    CollegeMapper collegeMapper;

    public List<College> getAll() {
        return collegeMapper.selectByExample(null);
    }

    public int saveCollege(College college) {
        return collegeMapper.insertSelective(college);
    }
}
