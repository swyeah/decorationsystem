package com.sise.po;

import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:30 2018/2/28
 */
public class SearchDesignerShow implements Serializable{
    private static final long serialVersionUID = 6427240462385992167L;
    private String sname;

    private Integer styleId;

    private Integer houseId;

    private Integer areaId;

    private Integer employeeId;

    public SearchDesignerShow() {
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    @Override
    public String toString() {
        return "SearchDesignerShow{" +
                "sname='" + sname + '\'' +
                ", styleId=" + styleId +
                ", houseId=" + houseId +
                ", areaId=" + areaId +
                '}';
    }
}
