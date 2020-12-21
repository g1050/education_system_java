package hm.com.service;

import hm.com.bean.Role;
import hm.com.bean.RoleExample;
import hm.com.dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/12/20 18:03
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    //密码正确返回old_id,密码错误返回-1
    public Integer verify(String username, String password, StringBuilder role) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);

        List<Role> roles = roleMapper.selectByExample(example);
        if(roles.size() == 0){//没有该用户
            return -1;
        }

        Role r= roles.get(0);
        role.append(r.getRole());
        if(r.getPassword().equals(password)){
            return r.getOldId();
        }else{
            return -1;
        }
    }
}
