package com.sise.service;

import com.sise.po.MaterialSmallclazz;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:31 2018/2/24
 */
public interface MaterialSmallclazzService {
    /*查找全部材料小分类*/
    public List<MaterialSmallclazz> searchAllMaterialSmallclazz();

    /*通过id查找材料小分类*/
    public MaterialSmallclazz searchMaterialSmallclazzById(Integer smallId);

    public List<MaterialSmallclazz> searchMaterialSmallclazzByBigId(Integer bigId);

    /*通过查询内容寻找材料小分类*/
    public List<MaterialSmallclazz> searchMaterialSmallclazzBySearchMaterialClazz(String searchContent);

    /*插入材料小分类*/
    public Integer saveMaterialSmallclazz(MaterialSmallclazz materialSmallclazz);

    /*更新材料小分类*/
    public Integer changeMaterialSmallclazz(MaterialSmallclazz materialSmallclazz);

    /*删除材料小分类*/
    public Integer dropMaterialSmallclazz(Integer smallId);
}
