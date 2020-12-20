package hm.com.service;

import hm.com.bean.Grade;
import hm.com.bean.GradeExample;
import hm.com.dao.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/12/20 15:43
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class GradeService {
    @Autowired
    GradeMapper gradeMapper;


    public void insert(Grade grade) {
        //如果该成绩存在需要更新
        GradeExample example = new GradeExample();
        GradeExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(grade.getCourseId()).andStudentIdEqualTo(grade.getStudentId());
        List<Grade> grades = gradeMapper.selectByExample(example);
        if(grades.size() > 0){
            grade.setId(grades.get(0).getId());
            gradeMapper.updateByPrimaryKeySelective(grade);
        }else{
            gradeMapper.insert(grade);
        }
    }

    public List<Grade> getGrade(Integer studentId) {
        return gradeMapper.selectByStudentId(studentId);
    }
}
