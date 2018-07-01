package com.sise.po;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:24 2018/3/3
 */
public class ProjectTime implements Serializable {
    private static final long serialVersionUID = -5921836216379467515L;

    private Integer projectId;

    private Integer registrationId;

    private Date projectDay;

    public ProjectTime() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Date getProjectDay() {
        return projectDay;
    }

    public void setProjectDay(Date projectDay) {
        this.projectDay = projectDay;
    }


    @Override
    public String toString() {
        return "ProjectTime{" +
                "projectId=" + projectId +
                ", registrationId=" + registrationId +
                ", projectDay=" + projectDay +
                '}';
    }
}
