package hm.com.service;
import hm.com.bean.Student;
import hm.com.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public void addStudent(Student student) {
         studentMapper.insertSelective(student);
         return;
    }

    public List<Student> getAll() {
        List<Student> data = studentMapper.selectByExample(null);
        return data;
    }

}

