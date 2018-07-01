package com.sise.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 15:21 2018/2/14
 */
public class CancelTable implements Serializable{

    private static final long serialVersionUID = 5776038795973620078L;
    private Integer cancelId;

    @NotNull(message = "飞单审批内容不能为空")
    @Size(min = 1,max = 50,message = "字数必须在1-50之间")
    private String cancelContent;

    private Date cancelTime;

    private Employee employee;

    @NotNull(message = "飞单审批人员不能为空")
    private Integer employeeId;

    @NotNull(message = "登记表记录不能为空")
    private Integer registrationId;

    private Date deleteTime;

    private boolean cancelStatus;

    public CancelTable() {
    }

    public CancelTable(String cancelContent, Date cancelTime, Integer employeeId, Integer registrationId, Date deleteTime, boolean cancelStatus) {
        this.cancelContent = cancelContent;
        this.cancelTime = cancelTime;
        this.employeeId = employeeId;
        this.registrationId = registrationId;
        this.deleteTime = deleteTime;
        this.cancelStatus = cancelStatus;
    }

    public Integer getCancelId() {
        return cancelId;
    }

    public void setCancelId(Integer cancelId) {
        this.cancelId = cancelId;
    }

    public String getCancelContent() {
        return cancelContent;
    }

    public void setCancelContent(String cancelContent) {
        this.cancelContent = cancelContent;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public boolean isCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(boolean cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    @Override
    public String toString() {
        return "CancelTable{" +
                "cancelId=" + cancelId +
                ", cancelContent='" + cancelContent + '\'' +
                ", cancelTime=" + cancelTime +
                ", employee=" + employee +
                ", employeeId=" + employeeId +
                ", registrationId=" + registrationId +
                ", deleteTime=" + deleteTime +
                ", cancelStatus=" + cancelStatus +
                '}';
    }
}
