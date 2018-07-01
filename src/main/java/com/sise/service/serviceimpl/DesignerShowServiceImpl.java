package com.sise.service.serviceimpl;

import com.sise.dao.DesignerShowDao;
import com.sise.po.*;
import com.sise.service.DesignerShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 13:34 2018/2/28
 */
@Service
public class DesignerShowServiceImpl implements DesignerShowService{
    @Autowired
    private DesignerShowDao designerShowDao;
    public Integer saveDesignerShow(DesignerShow designerShow) {
        return designerShowDao.insertDesignerShow(designerShow);
    }

    public Integer saveDesignerShowpic(List<DesignerShowpic> designerShowpics) {
        Integer column = 0;
        for (DesignerShowpic d:designerShowpics){
            designerShowDao.insertDesignerShowpic(d);
            column = column+1;
        }
        return column;
    }

    public Integer changeDesignerShow(DesignerShow designerShow) {
        Integer column = designerShowDao.updateDesignerShow(designerShow);
        return column;
    }

    public Integer dropDesignerShowByShowId(Integer showId) {
        designerShowDao.delCollectShow(showId);
        designerShowDao.delPraise(showId);
        Integer column = designerShowDao.delDesignerShowByShowId(showId);
        return column;
    }

    public Integer dropDesignerShowpicByShowId(Integer showId) {

        Integer column = designerShowDao.delDesignerShowpicByShowId(showId);
        return column;
    }

    public Integer getCountPraiseByShowId(Integer showId) {
        Integer column = designerShowDao.getCountPraiseByShowId(showId);
        return column;
    }

    public Integer getCountCollectionByShowId(Integer showId) {
        Integer column = designerShowDao.getCountCollectionByShowId(showId);
        return column;
    }

    public Integer savePraise(Praise praise) {
        Integer column = designerShowDao.insertPraise(praise);
        return column;
    }

    public Integer saveCollectionShow(CollectionShow collectionShow) {
        Integer column = designerShowDao.insertCollectionShow(collectionShow);
        return column;
    }

    public Integer changTitlepicByShowId(Integer showId, String showTitlepic) {
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("showId",showId);
        map.put("showTitlepic",showTitlepic);
        Integer column = designerShowDao.updateTitlePic(map);
        return column;
    }

    public DesignerShow searchDesignerShowByShowId(Integer showId) {
        return designerShowDao.findDesignerShowByShowId(showId);
    }

    public List<DesignerShow> searchDesignerByEmployeeId(SearchDesignerShow searchDesignerShow) {
        return designerShowDao.findDesignerByEmployeeId(searchDesignerShow);
    }

    public List<DesignerShow> searchDesignerToIndex(SearchDesignerShow searchDesignerShow) {
        return designerShowDao.findDesignerToIndex(searchDesignerShow);
    }

    public boolean findPraiseByShowId(Praise praise) {
        Praise praise1 =designerShowDao.findPraiseByShowId(praise);
        if (praise1!=null) {
            return true;
        }else {
            return false;
        }

    }

    public boolean findCollectByShowId(CollectionShow collectionShow) {
        CollectionShow collectionShow1 = designerShowDao.findCollectByShowId(collectionShow);
        if (collectionShow1!=null) {
            return true;
        }else {
            return false;
        }
    }
}
