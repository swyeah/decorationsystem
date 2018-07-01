package dao;

import com.sise.dao.MaterialClazzDao;
import com.sise.dao.MaterialDao;
import com.sise.dao.MaterialOfferDao;
import com.sise.dao.MaterialSmallclazzDao;
import com.sise.po.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:47 2018/2/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class MaterialTest {
    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private MaterialOfferDao materialOfferDao;

    @Autowired
    private MaterialClazzDao materialClazzDao;

    @Autowired
    private MaterialSmallclazzDao materialSmallclazzDao;

    @Test
    public void findMaterialyByIdTest(){
        Material material = materialDao.findMaterialyById(1);
        System.out.println(material);

    }
    @Test
    public void findMaterialFileByMaterialIdTest(){
        List<MaterialFile> materialFiles = materialDao.findMaterialFileByMaterialId(10);
        for (MaterialFile m:materialFiles){
            System.out.println(m.toString());
        }
    }
    @Test
    public void insertMaterialTest(){
           for (int i=0;i<10;i++){
               MaterialOffer materialOffer = new MaterialOffer();
               materialOffer.setOfferId(1);
               MaterialSmallclazz materialSmallclazz = new MaterialSmallclazz();
               materialSmallclazz.setSmallId(29);
               Material material = new Material("测试", "测试描述",new BigDecimal("22"), "测试单位","测试规格", "测试品牌",materialOffer ,  materialSmallclazz, new Date() );
               Integer materialId = materialDao.insertMaterial(material);
               System.out.println("更新后获得主键值"+material.getMaterialId());
           }

    }
    @Test
    public void delMaterialByIdTest(){
        materialDao.delMaterialById(12);

    }
    @Test
    public void insertMaterialFileTest(){
        for(int i=0;i<10;i++){
            MaterialFile materialFile = new MaterialFile("2131","121432",164);
            materialDao.insertMaterialFile(materialFile);
        }
    }

    @Test
    public void getVersionByIdTest(){
        Integer version = materialDao.getVersionById(10);
        System.out.println(version);

    }
    @Test
    public void updateMaterialTest(){
        MaterialOffer materialOffer = new MaterialOffer();
        materialOffer.setOfferId(1);
        MaterialSmallclazz materialSmallclazz = new MaterialSmallclazz();
        materialSmallclazz.setSmallId(2);
        Material material = new Material("测试1", "测试描述1",new BigDecimal("33"), "测试单位1","测试规格1", "测试品牌1",materialOffer ,  materialSmallclazz, new Date() );
        material.setMaterialId(10);
        material.setVersion(2);
        materialDao.updateMaterial(material);
        System.out.println(material);
    }

    @Test
    public void delAllMaterialFileByMaterialIdTest(){
        materialDao.delAllMaterialFileByMaterialId(10);

    }
    @Test
    public void insertMaterialOfferTest(){
        for(int i=0;i<10;i++){
            MaterialOffer materialOffer = new MaterialOffer();
            materialOffer.setOfferName("xx"+i);
            materialOffer.setOfferAddress("xxxxxx"+1);
            materialOffer.setOfferPhone("12345678912");
            materialOfferDao.insertMaterialOffer(materialOffer);
        }
    }

    @Test
    public void insertMaterialSmallclazzTest(){
        for(int i=0;i<10;i++){
            MaterialSmallclazz materialSmallclazz = new MaterialSmallclazz();
            materialSmallclazz.setSmallName("xx"+i);
            materialSmallclazz.setSmallDescribe("xxxxxx"+1);
            materialSmallclazz.setBigId(8);
            materialSmallclazzDao.insertMaterialSmallclazz(materialSmallclazz);
        }
    }

    @Test
    public void insertMaterialClazz(){
        for(int i=2;i<5;i++){
            MaterialClazz materialClazz = new MaterialClazz();
            materialClazz.setBigName("测试"+i);
            materialClazzDao.insertMaterialClazz(materialClazz);
        }
    }
    @Test
    public void Test(){
        List<Material> materials = materialDao.findMaterialBySmallId(7);
        for (Material m:materials){
            Integer i=materialDao.delAllMaterialFileByMaterialId(m.getMaterialId());
            System.out.println(m);
        }
    }

    @Test
    public void findMaterialByOfferIdTest(){
        List<Material> materials = materialOfferDao.findMaterialByOfferId(34);
        for (Material m:materials){
            System.out.println(m);
        }
    }



}
