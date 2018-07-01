package com.sise.po;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:32 2018/3/3
 */
public class ProgressContent implements Serializable {
    private static final long serialVersionUID = -3387746894609220798L;

    private Integer progressId;
    @NotNull(message = "请重新访问！")
    private Integer projectId;
    @NotNull(message = "标题不能为空")
    private String progressTitle;
    @NotNull(message = "内容不能为空！")
    private String progressContent;

    private Employee employee;

    private Integer version;

    private Date uploadTime;
    public ProgressContent() {
    }

    @Override
    public String toString() {
        return "ProgressContent{" +
                "progressId=" + progressId +
                ", projectId=" + projectId +
                ", progressTitle='" + progressTitle + '\'' +
                ", progressContent='" + progressContent + '\'' +
                ", employee=" + employee +
                ", version=" + version +
                ", uploadTime=" + uploadTime +
                '}';
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getProgressId() {
        return progressId;
    }

    public void setProgressId(Integer progressId) {
        this.progressId = progressId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProgressTitle() {
        return progressTitle;
    }

    public void setProgressTitle(String progressTitle) {
        this.progressTitle = progressTitle;
    }

    public String getProgressContent() {
        return progressContent;
    }

    public void setProgressContent(String progressContent) {
        this.progressContent = progressContent;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
