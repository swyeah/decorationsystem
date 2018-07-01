package com.sise.po;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:55 2018/2/4
 */
public class Job {

    private Integer jobId;

    private String jobName;

    private Department department;

    private List<MyAuth> myAuths;

    public Job() {
    }

    public Job(Integer jobId, String jobName, Department department) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public List<MyAuth> getMyAuths() {
        return myAuths;
    }

    public void setMyAuths(List<MyAuth> myAuths) {
        this.myAuths = myAuths;
    }
}
