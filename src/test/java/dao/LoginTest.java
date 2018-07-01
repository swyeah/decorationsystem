package dao;

import com.sise.dao.LoginDao;
import com.sise.po.Consumer;
import com.sise.po.Employee;
import com.sise.po.MyAuth;
import com.sise.service.LoginService;
import com.sise.util.MD5Utils;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class LoginTest {
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private LoginService loginService;

    @Test
    public void testLogin(){
        Consumer name = loginDao.searchByConsumer("111","111");
        if (name==null){
            System.out.println("222222222222222222222222222222222222");
        }
        System.out.println(MD5Utils.generatePassword("4563453453453453"));


    }
    @Test
    public void testCheckAdmin(){
        Consumer consumer = new Consumer();
        consumer.setUserName("101");
        consumer.setPassword("");
        System.out.println(consumer.toString());
    }
    @Test
    public void testCheck(){
        String result = loginService.checkName("11111");
        if (result==null){
            System.out.println("用户名可以使用");
        }else{
            System.out.println("用户名已存在");
        }
    }
    @Test
    public void testSaveConsumer(){
        Consumer consumer = new Consumer(null,"test1","test1","测试",new Date(System.currentTimeMillis())
                ,"123456789123456789","住址","","12345678912",new java.util.Date());
        System.out.println("插入"+consumer.toString());
        Integer result = loginService.saveConsumer(consumer);
        System.out.println(consumer.toString());
        System.out.println("插入记录数为"+result);
    }

    @Test
    public void Test(){
        Employee employee = loginService.loginEmployee(10101,"E10ADC3949BA59ABBE56E057F20F883E");
        System.out.println(employee);
        System.out.println(employee.getJob());
        List<MyAuth> myAuths = employee.getJob().getMyAuths();
        for (MyAuth m:myAuths){
            System.out.println(m);
        }
    }
}
