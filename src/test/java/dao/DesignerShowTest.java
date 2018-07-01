package dao;

import com.sise.dao.DesignerShowDao;
import com.sise.po.*;
import com.sise.service.DepositService;
import com.sise.service.DesignerShowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author:swye
 * @Description:
 * @Date:Create in 11:01 2018/2/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ssm/applicationContext.xml"})
public class DesignerShowTest {
    @Autowired
    private DesignerShowDao designerShowDao;

    @Autowired
    private DesignerShowService designerShowService;

    @Test
    public void insertDesignerShowTest(){
        DecorationStyle decorationStyle = new DecorationStyle(1);
        HouseType houseType = new HouseType(1);
        AreaType areaType = new AreaType(1);
        DesignerShow designerShow = new DesignerShow("标题","内容",decorationStyle,houseType,areaType,10002,new Date());
        designerShowDao.insertDesignerShow(designerShow);
        System.out.println("成功插入返回主键是："+designerShow.getShowId());
    }
    /*插入展示文章图片*/
    @Test
    public void insertDesignerShowpicTest(){
        DesignerShowpic designerShowpic = new DesignerShowpic("234r23",1);
        designerShowDao.insertDesignerShowpic(designerShowpic);
    }

    /*更新展示文章*/
    @Test
    public void updateDesignerShowTest( ){
        DecorationStyle decorationStyle = new DecorationStyle(1);
        HouseType houseType = new HouseType(1);
        AreaType areaType = new AreaType(1);

        DesignerShow designerShow = new DesignerShow("标题","内容",decorationStyle,houseType,areaType,10002,new Date());
        designerShow.setShowId(1);
        designerShowDao.updateDesignerShow(designerShow);
    }

    /*删除展示文章*/
    @Test
    public void delDesignerShowByShowIdTest( ){
        Integer i = designerShowDao.delDesignerShowByShowId(2);
        System.out.println(i);
    }

    /*删除所有文章的图片*/
    @Test
    public void delDesignerShowpicByShowIdTest( ){
        Integer i = designerShowDao.delDesignerShowpicByShowId(1);
        System.out.println(i);
    }

    /*得到文章的点赞次数*/
    @Test
    public void getCountPraiseByShowIdTest( ){
        System.out.println(designerShowDao.getCountPraiseByShowId(1));
    }

    /*得到文章的收藏次数*/
    @Test
    public void getCountCollectionByShowIdTest( ){
        System.out.println(designerShowDao.getCountCollectionByShowId(1));
    }

    /*插入点赞记录*/
    @Test
    public void insertPraiseTest( ){
        Praise praise = new Praise(23,1);
        designerShowDao.insertPraise(praise);
    }

    /*插入收藏记录*/
    @Test
    public void insertCollectionShowTest( ){
        CollectionShow collectionShow = new CollectionShow(23,1);
        designerShowDao.insertCollectionShow(collectionShow);
    }

    @Test
    public void Test(){
        Map<String , Object> map = new HashMap<String ,Object>();
        map.put("showId",1);
        map.put("showTitlepic","更新测试");
        Integer column = designerShowDao.updateTitlePic(map);

    }

    @Test
    public void findDesignerShowByShowIdTest(){
        DesignerShow designerShow = designerShowDao.findDesignerShowByShowId(1);
        System.out.println(designerShow.toString());
    }

    @Test
    public void findPraiseByShowIdTest(){
        Praise praise = new Praise(1,1);
        boolean s = designerShowService.findPraiseByShowId(praise);
        System.out.println(s);

        CollectionShow collectionShow = new CollectionShow(1,13);
        boolean r = designerShowService.findCollectByShowId(collectionShow);
        System.out.println(r);
    }


}
