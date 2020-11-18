package hm.com.service;

import hm.com.bean.College;
import hm.com.bean.CollegeExample;
import hm.com.dao.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author £ºsky
 * @date £ºCreated in 2020/11/13 11:48
 * @description£º
 * @modified By£º
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

    public int updateCollege(College college) {
        return  collegeMapper.updateByPrimaryKey(college);
    }

    public int deleteCollege(Integer id) {
        return collegeMapper.deleteByPrimaryKey(id);
    }

    public int deleteColleges(List<Integer> ids) {
        CollegeExample example = new CollegeExample();
        CollegeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return  collegeMapper.deleteByExample(example);
    }
}
