package com.sise.po;

import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 17:57 2018/2/24
 */
public class SearchContract {

    private String contractName;

    private Date startTime;

    private Date endTime;

    private Integer modeId;

    private Integer employeeId;

    public SearchContract() {
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
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

    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "SearchContract{" +
                "contractName='" + contractName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", modeId=" + modeId +
                '}';
    }
}
