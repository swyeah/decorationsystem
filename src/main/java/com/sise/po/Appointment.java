package com.sise.po;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:11 2018/2/15
 */
public class Appointment {

    private Integer appointmentId;

    @NotNull(message = "不能为空")
    private String appointmentTime;

    private Employee designer;

    private Integer designerId;

    private Integer registrationId;

    private boolean finishStatus;

    @NotNull(message = "不能为空")
    private String appointmentAddress;


    public Appointment() {
    }

    public Appointment(Integer appointmentId, String appointmentTime, Employee designer, Integer designerId, Integer registrationId, boolean finishStatus, String appointmentAddress) {
        this.appointmentId = appointmentId;
        this.appointmentTime = appointmentTime;
        this.designer = designer;
        this.designerId = designerId;
        this.registrationId = registrationId;
        this.finishStatus = finishStatus;
        this.appointmentAddress = appointmentAddress;
    }

    public String getAppointmentAddress() {
        return appointmentAddress;
    }

    public void setAppointmentAddress(String appointmentAddress) {
        this.appointmentAddress = appointmentAddress;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Employee getDesigner() {
        return designer;
    }

    public void setDesigner(Employee designer) {
        this.designer = designer;
    }

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public boolean isFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(boolean finishStatus) {
        this.finishStatus = finishStatus;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", designer=" + designer +
                ", designerId=" + designerId +
                ", registrationId=" + registrationId +
                ", finishStatus=" + finishStatus +
                ", appointmentAddress='" + appointmentAddress + '\'' +
                '}';
    }
}
