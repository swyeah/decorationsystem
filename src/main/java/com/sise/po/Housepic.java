package com.sise.po;

import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:16 2018/2/20
 */
/*户型图资料*/
public class Housepic {

    private  Integer housepicId;

    private String housepicName;

    private Integer registrationId;

    private String housepicDescrible;

    private HouseType houseType;

    private AreaType areaType;

    private Date uploadTime;

    private Integer status;

    private HousepicFile housepicFile;

    private Integer version;

    public Housepic() {
    }

    public Housepic(String housepicName, Integer registrationId, String housepicDescrible, HouseType houseType, AreaType areaType, Date uploadTime,Integer status) {
        this.housepicName = housepicName;
        this.registrationId = registrationId;
        this.housepicDescrible = housepicDescrible;
        this.houseType = houseType;
        this.areaType = areaType;
        this.uploadTime = uploadTime;
        this.status = status;
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

    public Integer getHousepicId() {
        return housepicId;
    }

    public void setHousepicId(Integer housepicId) {
        this.housepicId = housepicId;
    }

    public String getHousepicName() {
        return housepicName;
    }

    public void setHousepicName(String housepicName) {
        this.housepicName = housepicName;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public String getHousepicDescrible() {
        return housepicDescrible;
    }

    public void setHousepicDescrible(String housepicDescrible) {
        this.housepicDescrible = housepicDescrible;
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

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public HousepicFile getHousepicFile() {
        return housepicFile;
    }

    public void setHousepicFile(HousepicFile housepicFile) {
        this.housepicFile = housepicFile;
    }

    @Override
    public String toString() {
        return "Housepic{" +
                "housepicId=" + housepicId +
                ", housepicName='" + housepicName + '\'' +
                ", registrationId=" + registrationId +
                ", housepicDescrible='" + housepicDescrible + '\'' +
                ", houseType=" + houseType +
                ", areaType=" + areaType +
                ", uploadTime=" + uploadTime +
                ", status=" + status +
                '}';
    }
}
