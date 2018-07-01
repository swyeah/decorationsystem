package com.sise.service;

import com.sise.po.MaterialClazz;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:31 2018/2/24
 */
public interface MaterialClazzService {
    /*查找全部材料大类*/
    public List<MaterialClazz> searchAllMaterClazz();

    /*通过id查找材料大类*/
    public MaterialClazz searchMaterClazzById(Integer bigId);

    /*通过搜索内容查找大分类*/
    public List<MaterialClazz> searchMaterialClazzBySearch(String searchContent);

    /*插入材料大类*/
    public Integer saveMaterialClazz(MaterialClazz materialClazz);

    /*更新材料大类*/
    public Integer changeMaterialClazz(MaterialClazz materialClazz);
    /*删除材料大类*/
    public String dropMaterialClazz(Integer bigId) throws IOException;
}
