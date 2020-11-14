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


//��Ҫ����SpringTest��Ԫģ��
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	CollegeMapper collegeMapper;
	@Autowired
	ManagerMapper managerMapper;
	@Test
	//����ѧԺ��Ϣ
	public void testCollegeMapper() {
//		//1.����springIOC����
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//		2.�������л�ȡmapper
//		ioc.getBean(CollegeMapper.class);
		
		//spring��Ԫ����
		
		//1.����springTestģ��
		//2.ָ��Spring�����ļ�λ��
		//3.ֱ��authoWiredҪʹ�õ����
		//System.out.println(collegeMapper);

//		//����
//		College c = new College("�����ѧԺ", "����������", 4, "XԺ��","15533052683");
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
