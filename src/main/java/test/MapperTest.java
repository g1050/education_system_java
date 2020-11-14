package test;

import java.util.List;
import hm.com.bean.College;
import hm.com.dao.CollegeMapper;
import hm.com.dao.ManagerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//需要导入SpringTest单元模块
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	CollegeMapper collegeMapper;
	@Autowired
	ManagerMapper managerMapper;
	@Test
	//测试学院信息
	public void testCollegeMapper() {
//		//1.创建springIOC容器
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//		2.从容器中获取mapper
//		ioc.getBean(CollegeMapper.class);
		
		//spring单元测试
		
		//1.导入springTest模块
		//2.指定Spring配置文件位置
		//3.直接authoWired要使用的组件
		//System.out.println(collegeMapper);

//		//插入
//		College c = new College("计算机学院", "长安区东区", 4, "X院长","15533052683");
//		//collegeMapper.insertSelective(c);
//		Manager m = new Manager("sky2", "123456", "m", 1, "123", "123abc@test.com", new Date());
//		System.out.println(m);
//		System.out.println(c);
//		managerMapper.insertSelective(m);

		List<College> list = collegeMapper.selectByExample(null);
		for( College college : list){
			System.out.println(college.getPresident());
		}
	}
	
}
