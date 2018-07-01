package com.sise.service;

import com.sise.po.MaterialOffer;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:31 2018/2/24
 */
public interface MaterialOfferService {
    /*查找全部提供商*/
    public List<MaterialOffer> searchAllMaterialOffer();

    /*通过id查找提供商*/
    public MaterialOffer searchMaterialOfferById(Integer offerId);

    /*插入材料供应商*/
    public Integer saveMaterialOffer(MaterialOffer materialOffer);

    /*更新材料供应商*/
    public Integer changeMaterialOffer(MaterialOffer materialOffer);

    /*删除*/
    public Integer dropMaterialOffer(Integer offerId);

    /*根据条件搜索供应商*/
    public List<MaterialOffer> searchMaterialOfferBySearchContent(String searchContent);
}
