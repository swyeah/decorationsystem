package com.sise.service;

import com.sise.po.Material;
import com.sise.po.MaterialFile;
import com.sise.po.SearchMaterial;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:32 2018/2/24
 */
public interface MaterialService {
    /*通过id查找材料*/
    public Material searchMaterialyById(Integer materialId);

    /*通过材料id查找材料图片*/
    public List<MaterialFile> searchMaterialFileByMaterialId(Integer materialId);

    /*插入材料*/
    public Integer saveMaterial(Material material);

    /*通过id删除材料*/
    public Integer dropMaterialById(Integer materialId);


    /*插入材料图片*/
    public Integer saveMaterialFile(List<MaterialFile> materialFiles);

    /*获得版本号*/
    public Integer searchVersionById( Integer materialId);

    /*查找所有材料*/
    public List<Material> searchAllMaterial(SearchMaterial searchMaterial);


    /*更新材料*/
    public Integer changeMaterial(Material material);

    /*删除材料*/
    public Integer dropAllMaterialFileByMaterialId( Integer materialId);


}
