package hm.com.dao;

import hm.com.bean.Manager;
import hm.com.bean.ManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManagerMapper {
    long countByExample(ManagerExample example);

    int deleteByExample(ManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    //Я��ѧԺ��Ϣ,���ϲ�ѯ
    List<Manager> selectByExampleWithCollege(ManagerExample example);

    //��ʾѧԺid
    List<Manager> selectByExample(ManagerExample example);

    Manager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByExample(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    List<Manager> selectAll();
}