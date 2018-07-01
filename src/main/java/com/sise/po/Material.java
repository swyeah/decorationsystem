package com.sise.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:59 2018/2/24
 */
public class Material implements Serializable {

    private static final long serialVersionUID = 3849399080035632575L;
    private Integer materialId;

    private String materialName;

    private String materialDescribe;

    private BigDecimal materialPrice;

    private String materialStandard;

    private String materialCompany;

    private String materialBrand;

    private MaterialOffer materialOffer;

    private MaterialSmallclazz materialSmallclazz;

    private Date uploadTime;

    private Integer version;

    public Material() {
    }

    public Material(String materialName, String materialDescribe, BigDecimal materialPrice, String materialStandard, String materialCompany, String materialBrand, MaterialOffer materialOffer, MaterialSmallclazz materialSmallclazz, Date uploadTime) {
        this.materialName = materialName;
        this.materialDescribe = materialDescribe;
        this.materialPrice = materialPrice;
        this.materialStandard = materialStandard;
        this.materialCompany = materialCompany;
        this.materialBrand = materialBrand;
        this.materialOffer = materialOffer;
        this.materialSmallclazz = materialSmallclazz;
        this.uploadTime = uploadTime;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialDescribe() {
        return materialDescribe;
    }

    public void setMaterialDescribe(String materialDescribe) {
        this.materialDescribe = materialDescribe;
    }

    public BigDecimal getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(BigDecimal materialPrice) {
        this.materialPrice = materialPrice;
    }

    public String getMaterialStandard() {
        return materialStandard;
    }

    public void setMaterialStandard(String materialStandard) {
        this.materialStandard = materialStandard;
    }

    public String getMaterialCompany() {
        return materialCompany;
    }

    public void setMaterialCompany(String materialCompany) {
        this.materialCompany = materialCompany;
    }

    public String getMaterialBrand() {
        return materialBrand;
    }

    public void setMaterialBrand(String materialBrand) {
        this.materialBrand = materialBrand;
    }

    public MaterialOffer getMaterialOffer() {
        return materialOffer;
    }

    public void setMaterialOffer(MaterialOffer materialOffer) {
        this.materialOffer = materialOffer;
    }

    public MaterialSmallclazz getMaterialSmallclazz() {
        return materialSmallclazz;
    }

    public void setMaterialSmallclazz(MaterialSmallclazz materialSmallclazz) {
        this.materialSmallclazz = materialSmallclazz;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialId=" + materialId +
                ", materialName='" + materialName + '\'' +
                ", materialDescribe='" + materialDescribe + '\'' +
                ", materialPrice=" + materialPrice +
                ", materialStandard='" + materialStandard + '\'' +
                ", materialCompany='" + materialCompany + '\'' +
                ", materialBrand='" + materialBrand + '\'' +
                ", materialOffer=" + materialOffer +
                ", materialSmallclazz=" + materialSmallclazz +
                ", uploadTime=" + uploadTime +
                ", version=" + version +
                '}';
    }
}
