package hm.com.service;
import hm.com.bean.*;
import hm.com.bean.Class;
import hm.com.dao.CourseToStudentMapper;
import hm.com.dao.RoleMapper;
import hm.com.dao.StudentMapper;
import hm.com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    RoleMapper roleMapper;

    public void addStudent(Student student) {
            studentMapper.insertSelective(student);
            StudentExample example = new StudentExample();
            StudentExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(student.getName()).andIdCardEqualTo(student.getIdCard());
            List<Student> students = studentMapper.selectByExample(example);
            //插入数据到role表
            if(students.size() > 0){
                Student managerRt = students.get(0);
                Role role = new Role();
                role.setOldId(managerRt.getId());
                role.setUsername(managerRt.getName());
                role.setPassword(Constant.DEFAULTPASSWORD);//默认密码
                role.setRole(Constant.STUDENT);
                roleMapper.insert(role);
            }
         return;
    }

    public List<Student> getAll() {
        List<Student> data = studentMapper.selectByExampleWithCollege(null);
        return data;
    }
    public void deleteStudent(int parseInt){
        studentMapper.deleteByPrimaryKey(parseInt);
        return;
    }
    public void deleteStudents(List<Integer> delIds){
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(delIds);
        studentMapper.deleteByExample(example);
        return;
    }
    public int  updateStduent(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student);
    }


    public List<Student> getStudentByName(String studentName) {
        List<Student> all = getAll();
        List<Student> list = new ArrayList<Student>();

        for(Student student : all){
            if(student.getName().equals(studentName)){
                System.out.println(student.getName());
                list.add(student);
            }
        }
        return list;
    }

    public List<Student> getStudentByCollege(String college) {
        List<Student> all = getAll();
        List<Student> list = new ArrayList<Student>();

        for(Student student : all){
            if(student.getCollege().getName().equals(college)){
                list.add(student);
            }
        }
        return list;
    }

    public List<Student> getStudentByMore(String college, String studentName) {
        List<Student> all = getAll();
        List<Student> list = new ArrayList<Student>();

        for(Student student : all){
            if(student.getCollege().getName().equals(college) && student.getName().equals(studentName)){
                list.add(student);
            }
        }
        return list;
    }

}

