package com.sise.po;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:12 2018/2/20
 */
/*面积类型*/
public class AreaType {

    private Integer areaId;

    private String areaName;

    public AreaType() {
    }

    public AreaType(Integer areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public AreaType(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "AreaType{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
