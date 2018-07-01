package com.sise.po;

import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 16:57 2018/3/8
 */
public class MyAuth implements Serializable {

    private static final long serialVersionUID = 8565630446608547060L;
    private Integer authId;

    private String authName;

    private String authResource;


    public MyAuth() {
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthResource() {
        return authResource;
    }

    public void setAuthResource(String authResource) {
        this.authResource = authResource;
    }

    @Override
    public String toString() {
        return "MyAuth{" +
                "authId=" + authId +
                ", authName='" + authName + '\'' +
                ", authResource='" + authResource + '\'' +
                '}';
    }
}
