package com.sise.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 9:58 2018/2/22
 */
public class Designerpic {

    private Integer designerpicId;

    private BigDecimal designerpicMoney;

    private String designerpicDescribe;

    private String designerpicName;

    private HouseType houseType;

    private AreaType areaType;

    private DecorationStyle decorationStyle;

    private Integer registrationId;

    private Date uploadTime;

    private Integer version;

    private Integer status;
    public Designerpic() {
    }

    public Designerpic(BigDecimal designerpicMoney, String designerpicDescribe, String designerpicName, HouseType houseType, AreaType areaType, DecorationStyle decorationStyle, Integer registrationId, Date uploadTime) {
        this.designerpicMoney = designerpicMoney;
        this.designerpicDescribe = designerpicDescribe;
        this.designerpicName = designerpicName;
        this.houseType = houseType;
        this.areaType = areaType;
        this.decorationStyle = decorationStyle;
        this.registrationId = registrationId;
        this.uploadTime = uploadTime;
    }

    public Integer getDesignerpicId() {
        return designerpicId;
    }

    public void setDesignerpicId(Integer designerpicId) {
        this.designerpicId = designerpicId;
    }

    public BigDecimal getDesignerpicMoney() {
        return designerpicMoney;
    }

    public void setDesignerpicMoney(BigDecimal designerpicMoney) {
        this.designerpicMoney = designerpicMoney;
    }

    public String getDesignerpicDescribe() {
        return designerpicDescribe;
    }

    public void setDesignerpicDescribe(String designerpicDescribe) {
        this.designerpicDescribe = designerpicDescribe;
    }

    public String getDesignerpicName() {
        return designerpicName;
    }

    public void setDesignerpicName(String designerpicName) {
        this.designerpicName = designerpicName;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public AreaType getAreaType() {
        return areaType;
    }

    public void setAreaType(AreaType areaType) {
        this.areaType = areaType;
    }

    public DecorationStyle getDecorationStyle() {
        return decorationStyle;
    }

    public void setDecorationStyle(DecorationStyle decorationStyle) {
        this.decorationStyle = decorationStyle;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Designerpic{" +
                "designerpicId=" + designerpicId +
                ", designerpicMoney=" + designerpicMoney +
                ", designerpicDescribe='" + designerpicDescribe + '\'' +
                ", designerpicName='" + designerpicName + '\'' +
                ", houseType=" + houseType +
                ", areaType=" + areaType +
                ", decorationStyle=" + decorationStyle +
                ", registrationId=" + registrationId +
                ", uploadTime=" + uploadTime +
                ", version=" + version +
                ", status=" + status +
                '}';
    }
}
