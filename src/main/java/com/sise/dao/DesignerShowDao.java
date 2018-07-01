package com.sise.dao;

import com.sise.po.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:19 2018/2/28
 */
@Repository
public interface DesignerShowDao {

    /*插入展示文章*/
    public Integer insertDesignerShow(DesignerShow designerShow);

    /*插入展示文章图片*/
    public Integer insertDesignerShowpic(DesignerShowpic designerShowpic);

    /*更新标题图片*/
    public Integer updateTitlePic(Map<String,Object> map);

    /*更新展示文章*/
    public Integer updateDesignerShow(DesignerShow designerShow);

    /*删除展示文章*/
    public Integer delDesignerShowByShowId(@Param("showId") Integer showId);

    /*删除所有文章的图片*/
    public Integer delDesignerShowpicByShowId(@Param("showId") Integer showId);

    /*得到文章的点赞次数*/
    public Integer getCountPraiseByShowId(@Param("showId") Integer showId);

    /*得到文章的收藏次数*/
    public Integer getCountCollectionByShowId(@Param("showId") Integer showId);

    /*插入点赞记录*/
    public Integer insertPraise(Praise praise);

    /*插入收藏记录*/
    public Integer insertCollectionShow(CollectionShow collectionShow);

    public Integer delCollectShow(@Param("showId") Integer showId);

    public Integer delPraise(@Param("showId") Integer showId);

    /*通过员工Id和条件查询*/
    public List<DesignerShow> findDesignerByEmployeeId(SearchDesignerShow searchDesignerShow);

    /*得到文章*/
    public DesignerShow findDesignerShowByShowId(@Param("showId") Integer showId);

    /*得到点赞具体人*/
    public Praise findPraiseByShowId(Praise praise);

    /*得到收藏具体人*/
    public CollectionShow findCollectByShowId(CollectionShow collectionShow);

    public List<DesignerShow> findDesignerToIndex(SearchDesignerShow searchDesignerShow);

}
