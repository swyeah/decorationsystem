package com.sise.service;

import com.sise.po.*;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 13:31 2018/2/28
 */
public interface DesignerShowService {

    /*插入展示文章*/
    public Integer saveDesignerShow(DesignerShow designerShow);

    /*插入展示文章图片*/
    public Integer saveDesignerShowpic(List<DesignerShowpic> designerShowpics);

    /*更新展示文章*/
    public Integer changeDesignerShow(DesignerShow designerShow);

    /*删除展示文章*/
    public Integer dropDesignerShowByShowId( Integer showId);

    /*删除所有文章的图片*/
    public Integer dropDesignerShowpicByShowId( Integer showId);

    /*得到文章的点赞次数*/
    public Integer getCountPraiseByShowId( Integer showId);

    /*得到文章的收藏次数*/
    public Integer getCountCollectionByShowId(Integer showId);

    /*插入点赞记录*/
    public Integer savePraise(Praise praise);

    /*插入收藏记录*/
    public Integer saveCollectionShow(CollectionShow collectionShow);

    public Integer changTitlepicByShowId( Integer showId,String showTitlepic);

    public DesignerShow searchDesignerShowByShowId(Integer showId);

    public List<DesignerShow> searchDesignerByEmployeeId(SearchDesignerShow searchDesignerShow);

    public List<DesignerShow> searchDesignerToIndex(SearchDesignerShow searchDesignerShow);

    /*得到点赞具体人*/
    public boolean findPraiseByShowId(Praise praise);

    /*得到收藏具体人*/
    public boolean findCollectByShowId(CollectionShow collectionShow);
}
