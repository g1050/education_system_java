package hm.com.service;

import hm.com.bean.*;
import hm.com.bean.Class;
import hm.com.dao.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ��sky
 * @date ��Created in 2020/11/22 16:34
 * @description��
 * @modified By��
 * @version: $
 */
@Service
public class ClassService {

    @Autowired
    ClassMapper classMapper;

    public List<Class> getAll() {
        //����dao�㣬dao�㸺������ݿ��в�ѯ����
        List<Class> list = classMapper.selectByExampleWithMajorAndCollege(null);
        //System.out.println(list.toString());
        //����Controller
        return list;
    }

    //����dao��������ݿ�
    public void addClass(Class aClass) {
        classMapper.insertSelective(aClass);
        return;
    }

    public void deleteClass(int parseInt) {
        classMapper.deleteByPrimaryKey(parseInt);
        return;
    }

    public void deleteClasses(List<Integer> delIds) {
        ClassExample example = new ClassExample();

        ClassExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(delIds);

        classMapper.deleteByExample(example);
        return ;
    }

    public int  updateClass(Class classa) {
        return classMapper.updateByPrimaryKeySelective(classa);
    }

    public List<Class> getClassByCollege(String collegeName) {
        List <Class> all = getAll();
        List <Class> res = new ArrayList<Class>();

        for(Class aclass : all){
            if(aclass.getCollege().getName().equals(collegeName)){
                res.add(aclass);
            }
        }
        return res;
    }

    public List<Class> getClassByName(String name) {
        List <Class> all = getAll();
        List <Class> res = new ArrayList<Class>();

        for(Class aclass : all){
            if(aclass.getName().equals(name)){
                res.add(aclass);
            }
        }
        return res;
    }
}
