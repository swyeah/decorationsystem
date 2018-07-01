package com.sise.dao;

import com.sise.po.MaterialSmallclazz;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:06 2018/2/24
 */
@Repository
public interface MaterialSmallclazzDao {
    /*查找全部材料小分类*/
    public List<MaterialSmallclazz> findAllMaterialSmallclazz();

    /*通过id查找材料小分类*/
    public MaterialSmallclazz findMaterialSmallclazzById(@Param("small_id") Integer smallId);

    /*通过大类id查找材料小分类*/
    public List<MaterialSmallclazz> findMaterialSmallclazzByBigId(@Param("bigId") Integer bigId);

    /*通过查询内容寻找材料小分类*/
    public List<MaterialSmallclazz> findMaterialSmallclazzBySearchMaterialClazz(@Param("searchContent") String searchContent);

    /*插入材料小分类*/
    public Integer insertMaterialSmallclazz(MaterialSmallclazz materialSmallclazz);

    /*更新材料小分类*/
    public Integer updateMaterialSmallclazz(MaterialSmallclazz materialSmallclazz);

    /*删除材料小分类*/
    public Integer delMaterialSmallclazz(@Param("smallId") Integer smallId);

    /*通过大分类删除小分类*/
    public Integer delMaterialSmallclazzByBigId(@Param("bigId") Integer bigId);
}
