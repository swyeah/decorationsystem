package com.sise.dao;

import com.sise.po.Material;
import com.sise.po.MaterialOffer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:05 2018/2/24
 */
@Repository
public interface MaterialOfferDao {

    /*查找全部提供商*/
    public List<MaterialOffer> findAllMaterialOffer();

    /*通过id查找提供商*/
    public MaterialOffer findMaterialOfferById(@Param("offer_id") Integer offerId);

    /*插入材料供应商*/
    public Integer insertMaterialOffer(MaterialOffer materialOffer);

    /*更新材料供应商*/
    public Integer updateMaterialOffer(MaterialOffer materialOffer);

    /*删除*/
    public Integer delMaterialOffer(@Param("offerId") Integer offerId);

    /*根据条件搜索供应商*/
    public List<MaterialOffer> findMaterialOfferBySearchContent(@Param("searchContent") String searchContent);

    /*查找出供应商的所有材料*/
    public List<Material> findMaterialByOfferId(@Param("offerId") Integer offerId);

}
