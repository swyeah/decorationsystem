package com.sise.po;

import java.sql.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 9:29 2018/2/17
 */
public class SearchContent {

    private Date startTime;

    private Date endTime;

    private String sname;

    private Integer provinceId;

    private Integer cityId;

    private Integer status;

    private Integer employeeId;

    private Employee employee;

    public SearchContent() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "SearchContent{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", sname='" + sname + '\'' +
                ", provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", status=" + status +
                ", employeeId=" + employeeId +
                ", employee=" + employee +
                '}';
    }

    public SearchContent(Date startTime, Date endTime, String sname, Integer provinceId, Integer cityId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.sname = sname;
        this.provinceId = provinceId;
        this.cityId = cityId;
    }
}
