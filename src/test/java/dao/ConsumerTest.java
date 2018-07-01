package dao;

import com.sise.dao.ConsumerDao;
import com.sise.po.City;
import com.sise.po.Consumer;
import com.sise.po.Province;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class ConsumerTest {
    @Autowired
    private ConsumerDao consumerDao;

    @Test
    public void findAllProvince(){
        //测试findAllProvince方法
        List<Province> provinces = consumerDao.findAllProvince();
        for (Province p:provinces){
            System.out.println(p.getProvinceId()+"       "+p.getInitials()+"    "+p.getProvinceName());
        }

    }

    @Test
    public void testFindCityByProvinceId(){
        //测试findCityByProvinceId方法
        List<City> cities = consumerDao.findCityByProvinceId(2);
        for (City c:cities){
            System.out.println(c.getCityId()+"    "+c.getCityName());
        }
    }

    @Test
    public void findConsumerByUserNameTest(){
        Consumer consumer = consumerDao.findConsumerByUserName("1111111");
        System.out.println(consumer.toString());
    }

}
