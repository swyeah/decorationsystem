package com.sise.po;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:00 2018/2/24
 */
public class SearchMaterial {

    private String MaterialName;

    private Integer offerId;

    private Integer bigId;

    private Integer smallId;

    public SearchMaterial() {
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Integer getBigId() {
        return bigId;
    }

    public void setBigId(Integer bigId) {
        this.bigId = bigId;
    }

    public Integer getSmallId() {
        return smallId;
    }

    public void setSmallId(Integer smallId) {
        this.smallId = smallId;
    }

    @Override
    public String toString() {
        return "SearchMaterial{" +
                "MaterialName='" + MaterialName + '\'' +
                ", offerId=" + offerId +
                ", bigId=" + bigId +
                ", smallId=" + smallId +
                '}';
    }
}
