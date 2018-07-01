package com.sise.po;

import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 17:09 2018/3/9
 */
public class Permission implements Serializable{
    private static final long serialVersionUID = 5887038726283193822L;
    private Integer permissionId;

    private Integer jobId;

    private Integer authId;

    public Permission() {
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", jobId=" + jobId +
                ", authId=" + authId +
                '}';
    }
}
