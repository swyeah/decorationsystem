package com.sise.service.serviceimpl;

import com.sise.dao.MaterialClazzDao;
import com.sise.dao.MaterialDao;
import com.sise.dao.MaterialSmallclazzDao;
import com.sise.po.Material;
import com.sise.po.MaterialClazz;
import com.sise.po.MaterialSmallclazz;
import com.sise.service.MaterialClazzService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:39 2018/2/24
 */
@Service
public class MaterialClazzServiceImpl implements MaterialClazzService {
    @Autowired
    private MaterialClazzDao materialClazzDao;

    @Autowired
    private MaterialSmallclazzDao materialSmallclazzDao;

    @Autowired
    private MaterialDao materialDao;
    public List<MaterialClazz> searchAllMaterClazz() {
        return materialClazzDao.findAllMaterClazz();
    }

    public MaterialClazz searchMaterClazzById(Integer bigId) {
        return materialClazzDao.findMaterClazzById(bigId);
    }

    public List<MaterialClazz> searchMaterialClazzBySearch(String searchContent) {
        return materialClazzDao.findMaterialClazzBySearch(searchContent);
    }

    public Integer saveMaterialClazz(MaterialClazz materialClazz) {
        return materialClazzDao.insertMaterialClazz(materialClazz);
    }

    public Integer changeMaterialClazz(MaterialClazz materialClazz) {
        return materialClazzDao.updateMaterialClazz(materialClazz);
    }

    public String dropMaterialClazz(Integer bigId) throws IOException {
        Integer smallColumn = 0;
        Integer materialColumn = 0;
        Integer picColumn =0;
        List<MaterialSmallclazz> materialSmallclazzes = materialSmallclazzDao.findMaterialSmallclazzByBigId(bigId);
        for (MaterialSmallclazz ms:materialSmallclazzes){
            List<Material> materials = materialDao.findMaterialBySmallId(ms.getSmallId());
            for (Material m:materials){
                Integer i=materialDao.delAllMaterialFileByMaterialId(m.getMaterialId());
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\material\\" + m.getMaterialSmallclazz().getSmallId() + "\\" + m.getMaterialId()));
                picColumn = picColumn+i;
            }
            Integer j = materialDao.delMaterialBySmallId(ms.getSmallId());
            materialColumn = materialColumn+j;
        }
        smallColumn= materialSmallclazzDao.delMaterialSmallclazzByBigId(bigId);
        Integer bigColumn = materialClazzDao.delMaterialClazz(bigId);
        String str = "总共删除材料大分类 "+bigColumn+"条记录，材料小分类 "+smallColumn+"条记录，材料 "+materialColumn+"条记录，材料图片 "+picColumn+"条记录";
        return str;
    }
}
