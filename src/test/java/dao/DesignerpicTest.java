package dao;

import com.sise.dao.DesignerpicDao;
import com.sise.po.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 11:17 2018/2/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class DesignerpicTest {
    @Autowired
    private DesignerpicDao designerpicDao;
    @Test
    public void findDesignerpicByRegistrationIdTest(){
        Designerpic designerpic =designerpicDao.findDesignerpicByRegistrationId(100014);
        System.out.println(designerpic.toString());
    }

    @Test
    public void insertDesignerTest(){
        BigDecimal designerpicMoney = new BigDecimal("22.3");
        designerpicMoney = designerpicMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
        HouseType houseType= new HouseType(3);
        AreaType areaType = new AreaType(3);
        DecorationStyle decorationStyle = new DecorationStyle(3);
        Designerpic designerpic = new Designerpic(designerpicMoney,"插入测试","插入测试1",houseType,areaType,decorationStyle,100005,new Date());
        Integer column = designerpicDao.insertDesigner(designerpic);
        System.out.println(column);
    }

    @Test
    public void insertDesignerpicFileTest(){
        DesignerpicFile designerpicFile = new DesignerpicFile();
        designerpicFile.setFileName("dfdsf");
        designerpicFile.setDesignerpicId(1);
        designerpicFile.setFileDescribe("dffs");
        designerpicFile.setFileUrl("dfdsfsdf");
        Integer column = designerpicDao.insertDesignerpicFile(designerpicFile);
        System.out.println(column);

    }

    @Test
    public  void findAllDesignerpicFileByDesignerIdTest(){
        List<DesignerpicFile> designerpicFiles = designerpicDao.findAllDesignerpicFileByDesignerId(1);
        for (DesignerpicFile d:designerpicFiles){
            System.out.println(d.toString());
        }
    }

    @Test
    public void updateDescribeByIdTest(){
        List<DesignerpicFile> designerpicFiles = designerpicDao.findAllDesignerpicFileByDesignerId(1);
        Map<String,Object> map = new HashMap<String ,Object>();
        for (DesignerpicFile d:designerpicFiles){
            map.put("fileId",d.getFileId());
            map.put("fileDescribe","222");
            Integer column = designerpicDao.updateDescribeById(map);
            System.out.println(column);
        }

    }


    @Test
    public void delDesignerpicByDesignerpicIdTest(){
        Integer column = designerpicDao.delDesignerpicByDesignerpicId(4);
        System.out.println(column);
    }

    @Test
    public void delDesignerpicFileByDesignerpicIdTest(){
        Integer column = designerpicDao.delDesignerpicFileByDesignerpicId(1);
        System.out.println(column);
    }

    @Test
    public void updateDesignerpicByDesignerpicIdTest(){
        BigDecimal designerpicMoney = new BigDecimal("22.3");
        designerpicMoney = designerpicMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
        HouseType houseType= new HouseType(4);
        AreaType areaType = new AreaType(4);
        DecorationStyle decorationStyle = new DecorationStyle(4);
        Designerpic designerpic = new Designerpic(designerpicMoney,"插入测试","插入测试1",houseType,areaType,decorationStyle,100005,new Date());
        designerpic.setDesignerpicId(1);
        designerpic.setVersion(2);
        Integer column = designerpicDao.updateDesignerpicByDesignerpicId(designerpic);
        System.out.println(column);

    }
    @Test
    public void getVersionByDesignerpicIdTest(){
        Integer version = designerpicDao.getVersionByDesignerpicId(1);
        System.out.println(version);
    }
}
