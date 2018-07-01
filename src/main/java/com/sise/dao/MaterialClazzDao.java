package com.sise.dao;

import com.sise.po.Material;
import com.sise.po.MaterialClazz;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:05 2018/2/24
 */
@Repository
public interface MaterialClazzDao {

    /*查找全部材料大类*/
    public List<MaterialClazz> findAllMaterClazz();

    /*通过id查找材料大类*/
    public MaterialClazz findMaterClazzById(@Param("big_id") Integer BigId);

    /*通过搜索内容查找大分类*/
    public List<MaterialClazz> findMaterialClazzBySearch(@Param("searchContent") String searchContent);

    /*插入材料大类*/
    public Integer insertMaterialClazz(MaterialClazz materialClazz);

    /*更新材料大类*/
    public Integer updateMaterialClazz(MaterialClazz materialClazz);
    /*删除材料大类*/
    public Integer delMaterialClazz(@Param("bigId") Integer bigId);


}
