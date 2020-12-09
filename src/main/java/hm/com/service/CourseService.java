package hm.com.service;

import hm.com.bean.Course;
import hm.com.bean.CourseExample;
import hm.com.bean.DormitoryExample;
import hm.com.dao.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;
    public List<Course> getAll(){
        //1.从数据库获取信息
        List <Course> list = courseMapper.selectByExample(null);
        //返回
        return list;
    }

    public void addCourse(Course course) {
        courseMapper.insertSelective(course);
        return ;
    }

    public int updateCourse(Course course) {
        return courseMapper.updateByPrimaryKey(course);
    }

    public int deleteCourse(Integer id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    public int deleteCourse(List<Integer> ids){
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return courseMapper.deleteByExample(example);
    }

}
