package dao;

import com.sise.dao.AreaTypeDao;
import com.sise.dao.HouseTypeDao;
import com.sise.dao.HousepicDao;
import com.sise.po.*;
import com.sise.service.CancelTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:55 2018/2/14
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class HousepicTest {
    @Autowired
    private HousepicDao housepicDao ;

    @Autowired
    private HouseTypeDao houseTypeDao;

    @Autowired
    private AreaTypeDao areaTypeDao;

    @Test
    public void insertHousepicTest(){
            HouseType houseType = new HouseType();
            houseType.setHouseId(1);
            AreaType areaType = new AreaType();
            areaType.setAreaId(1);
            Housepic housepic = new Housepic();
            housepic.setHousepicName("测试");
            housepic.setRegistrationId(1000023);
            housepic.setHousepicDescrible("测试描述");
            housepic.setHouseType(houseType);
            housepic.setAreaType(areaType);
            housepic.setUploadTime(new Date());
            housepic.setStatus(2);
            System.out.println(housepic.toString());
            Integer housepicId = housepicDao.insertHousepic(housepic);
        System.out.println(housepicId);
    }

    @Test
    public void searchHousepicByRegistrationIdTest(){
        Housepic housepic = housepicDao.searchHousepicByRegistrationId(100001);
        System.out.println(housepic.toString());
        System.out.println(housepic.getAreaType().toString());
        System.out.println(housepic.getHouseType().toString());
    }

    @Test
    public void searchStatusByIdTest(){
        Integer column = housepicDao.searchStatusById(3);
        System.out.println(column);
    }

    @Test
    public void delHousepicByIdTest(){
        Integer column = housepicDao.delHousepicById(5);
        System.out.println(column);
    }

    @Test
    public void updateStatusByIdTest(){
        Integer column = housepicDao.updateStatusById(2,3);
    }

    @Test
    public void searchHousepicFileByHousepicIdTest(){
        List<HousepicFile> housepicFiles = housepicDao.searchHousepicFileByHousepicId(2);
        for (HousepicFile h:housepicFiles){
            System.out.println(h.toString());
        }
    }

    @Test
    public void searchAllAreaTypeTest(){
        List<HouseType> houseTypes = houseTypeDao.searchAllHouseType();
        for (HouseType h:houseTypes) {
            System.out.println(h.toString());
        }
        List<AreaType> areaTypes = areaTypeDao.searchAllAreaType();
        for (AreaType a:areaTypes){
            System.out.println(a.toString());
        }
        }

        @Test
    public void Test(){
        //housepicDao.delAllHousepicFileByHousepicId()
           Integer version =  housepicDao.getVersionById(47);
            System.out.println(version);
        }


}
