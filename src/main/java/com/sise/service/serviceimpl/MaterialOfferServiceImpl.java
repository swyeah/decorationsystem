package com.sise.service.serviceimpl;

import com.sise.dao.MaterialDao;
import com.sise.dao.MaterialOfferDao;
import com.sise.po.Material;
import com.sise.po.MaterialOffer;
import com.sise.service.MaterialOfferService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:37 2018/2/24
 */
@Service
public class MaterialOfferServiceImpl implements MaterialOfferService{
    @Autowired
    private MaterialOfferDao materialOfferDao;

    @Autowired
    private MaterialDao materialDao;
    public List<MaterialOffer> searchAllMaterialOffer() {
        return materialOfferDao.findAllMaterialOffer();
    }

    public MaterialOffer searchMaterialOfferById(Integer offerId) {
        return materialOfferDao.findMaterialOfferById(offerId);
    }

    public Integer saveMaterialOffer(MaterialOffer materialOffer) {
        return materialOfferDao.insertMaterialOffer(materialOffer);
    }

    public Integer changeMaterialOffer(MaterialOffer materialOffer) {
        return materialOfferDao.updateMaterialOffer(materialOffer);
    }

    public Integer dropMaterialOffer(Integer offerId) {
        List<Material> materials = materialOfferDao.findMaterialByOfferId(offerId);
        for (Material m:materials){
            Integer i=materialDao.delAllMaterialFileByMaterialId(m.getMaterialId());

            try {
                FileUtils.deleteDirectory(new File("D:\\ProjectUpload\\material\\" + m.getMaterialSmallclazz().getSmallId() + "\\" + m.getMaterialId()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            materialDao.delMaterialById(m.getMaterialId());
        }
        return materialOfferDao.delMaterialOffer(offerId);
    }

    public List<MaterialOffer> searchMaterialOfferBySearchContent(String searchContent) {
        return materialOfferDao.findMaterialOfferBySearchContent(searchContent);
    }
}
