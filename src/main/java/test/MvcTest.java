package test;

import com.github.pagehelper.PageInfo;
import hm.com.bean.College;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/11/13 12:16
 * @description：测试请求,使用spring提供的测试请求模块
 * @modified By：
 * @version: 0.1$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {

    //传入springMvc的ioc
    @Autowired
    WebApplicationContext webApplicationContext;
    //虚拟mvc请求
    MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testPage() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/retrieve/college").param("pn","1")).andReturn();

        MockHttpServletRequest request = result.getRequest();
        PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
        System.out.println(pi.getPageNum());
        System.out.println(pi.getPages());
        System.out.println(pi.getTotal());
        int []nums = pi.getNavigatepageNums();
        for(int i : nums){
            System.out.println(" " + i);
        }

        //获取数据
        List<College> list = pi.getList();
        for(College college : list){
            System.out.println("Id," + college.getId());
            System.out.println("username " + college.getName());
        }

    }
}
