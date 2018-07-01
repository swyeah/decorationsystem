package com.sise.po;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:13 2018/2/20
 */
public class HouseType {

    private Integer houseId;

    private String houseName;

    public HouseType() {
    }

    public HouseType(Integer houseId, String houseName) {
        this.houseId = houseId;
        this.houseName = houseName;
    }

    public HouseType(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    @Override
    public String toString() {
        return "HouseType{" +
                "houseId=" + houseId +
                ", houseName='" + houseName + '\'' +
                '}';
    }
}
