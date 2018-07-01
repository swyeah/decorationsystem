package com.sise.service.serviceimpl;

import com.sise.dao.MaterialDao;
import com.sise.po.Material;
import com.sise.po.MaterialFile;
import com.sise.po.SearchMaterial;
import com.sise.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:42 2018/2/24
 */
@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialDao materialDao;
    public Material searchMaterialyById(Integer materialId) {
        return materialDao.findMaterialyById(materialId);
    }

    public List<MaterialFile> searchMaterialFileByMaterialId(Integer materialId) {
        return materialDao.findMaterialFileByMaterialId(materialId);
    }

    public Integer saveMaterial(Material material) {
        return materialDao.insertMaterial(material);
    }

    public Integer dropMaterialById(Integer materialId) {
        return materialDao.delMaterialById(materialId);
    }

    public Integer saveMaterialFile(List<MaterialFile> materialFiles) {
        Integer count = 0;
        for (MaterialFile m:materialFiles){
            Integer column =  materialDao.insertMaterialFile(m);
            count = count+column;
        }
        return count;

    }

    public Integer searchVersionById(Integer materialId) {
        return materialDao.getVersionById(materialId);
    }

    public List<Material> searchAllMaterial(SearchMaterial searchMaterial) {
        return materialDao.findAllMaterial(searchMaterial);
    }

    public Integer changeMaterial(Material material) {
        return materialDao.updateMaterial(material);
    }

    public Integer dropAllMaterialFileByMaterialId(Integer materialId) {
        return materialDao.delAllMaterialFileByMaterialId(materialId);
    }
}
