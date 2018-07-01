package com.sise.po;

import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 16:04 2018/2/24
 */
public class SearchDesignerpic {

    private String designerpicName;

    private Date startTime;

    private Date endTime;

    private Integer houseId;

    private Integer styleId;

    private Integer areaId;

    private Integer employeeId;

    public SearchDesignerpic() {
    }

    public String getDesignerpicName() {
        return designerpicName;
    }

    public void setDesignerpicName(String designerpicName) {
        this.designerpicName = designerpicName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "SearchDesignerpic{" +
                "designerpicName='" + designerpicName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", houseId=" + houseId +
                ", styleId=" + styleId +
                '}';
    }
}
