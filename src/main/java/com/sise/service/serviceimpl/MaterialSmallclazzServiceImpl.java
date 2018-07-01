package com.sise.service.serviceimpl;

import com.sise.dao.MaterialDao;
import com.sise.dao.MaterialSmallclazzDao;
import com.sise.po.Material;
import com.sise.po.MaterialSmallclazz;
import com.sise.service.MaterialSmallclazzService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:40 2018/2/24
 */
@Service
public class MaterialSmallclazzServiceImpl implements MaterialSmallclazzService {
    @Autowired
    private MaterialSmallclazzDao materialSmallclazzDao;

    @Autowired
    private MaterialDao materialDao;
    public List<MaterialSmallclazz> searchAllMaterialSmallclazz() {
        return materialSmallclazzDao.findAllMaterialSmallclazz();
    }

    public MaterialSmallclazz searchMaterialSmallclazzById(Integer smallId) {
        return materialSmallclazzDao.findMaterialSmallclazzById(smallId);
    }

    public List<MaterialSmallclazz> searchMaterialSmallclazzByBigId(Integer bigId) {
        return materialSmallclazzDao.findMaterialSmallclazzByBigId(bigId);
    }

    public List<MaterialSmallclazz> searchMaterialSmallclazzBySearchMaterialClazz(String searchContent) {
        return materialSmallclazzDao.findMaterialSmallclazzBySearchMaterialClazz(searchContent);
    }

    public Integer saveMaterialSmallclazz(MaterialSmallclazz materialSmallclazz) {
        return materialSmallclazzDao.insertMaterialSmallclazz(materialSmallclazz);
    }

    public Integer changeMaterialSmallclazz(MaterialSmallclazz materialSmallclazz) {
        return materialSmallclazzDao.updateMaterialSmallclazz(materialSmallclazz);
    }

    public Integer dropMaterialSmallclazz(Integer smallId) {
        List<Material> materials = materialDao.findMaterialBySmallId(smallId);
        for (Material m:materials){
            Integer i=materialDao.delAllMaterialFileByMaterialId(m.getMaterialId());

            try {
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\material\\" + m.getMaterialSmallclazz().getSmallId() + "\\" + m.getMaterialId()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            materialDao.delMaterialBySmallId(smallId);
        }
        return materialSmallclazzDao.delMaterialSmallclazz(smallId);
    }
}
