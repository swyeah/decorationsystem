package com.sise.po;

import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 11:18 2018/2/24
 */
public class SearchHousepic {

    private String housepicName;

    private Date startTime;

    private Date endTime;

    private Integer areaId;

    private Integer houseId;

    private Integer employeeId;

    public SearchHousepic() {
    }

    public String getHousepicName() {
        return housepicName;
    }

    public void setHousepicName(String housepicName) {
        this.housepicName = housepicName;
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

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "SearchHousepic{" +
                "housepicName='" + housepicName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", areaId=" + areaId +
                ", houseId=" + houseId +
                '}';
    }
}
