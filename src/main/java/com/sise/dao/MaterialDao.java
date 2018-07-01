package com.sise.dao;

import com.sise.po.Material;
import com.sise.po.MaterialFile;
import com.sise.po.SearchMaterial;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:06 2018/2/24
 */
@Repository
public interface MaterialDao {

    /*通过id查找材料*/
    public Material findMaterialyById(@Param("materialId") Integer materialId);

    /*通过材料id查找材料图片*/
    public List<MaterialFile> findMaterialFileByMaterialId(@Param("materialId") Integer materialId);

    /*插入材料*/
    public Integer insertMaterial(Material material);

    /*通过id删除材料*/
    public Integer delMaterialById(@Param("materialId") Integer materialId);


    /*插入材料图片*/
    public Integer insertMaterialFile(MaterialFile materialFile);

    /*获得版本号*/
    public Integer getVersionById(@Param("materialId") Integer materialId);

    /*查找所有材料*/
    public List<Material> findAllMaterial(SearchMaterial searchMaterial);


    /*更新材料*/
    public Integer updateMaterial(Material material);

    /*删除材料*/
    public Integer delAllMaterialFileByMaterialId(@Param("materialId") Integer materialId);

    /*通过材料小分类查找材料*/
    public List<Material> findMaterialBySmallId(@Param("smallId") Integer smallId);

    /*通过材料小分类id删除材料*/
    public Integer delMaterialBySmallId(@Param("smallId") Integer smallId);



}
