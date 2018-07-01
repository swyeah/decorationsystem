package com.sise.po;

import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:17 2018/2/28
 */
public class Praise implements Serializable {
    private static final long serialVersionUID = -2833419693224482168L;

    private Integer praiseId;

    private Integer praiseMan;

    private Integer praiseShow;

    public Praise() {
    }

    public Praise(Integer praiseMan, Integer praiseShow) {
        this.praiseMan = praiseMan;
        this.praiseShow = praiseShow;
    }

    public Integer getPraiseId() {
        return praiseId;
    }

    public void setPraiseId(Integer praiseId) {
        this.praiseId = praiseId;
    }

    public Integer getPraiseMan() {
        return praiseMan;
    }

    public void setPraiseMan(Integer praiseMan) {
        this.praiseMan = praiseMan;
    }

    public Integer getPraiseShow() {
        return praiseShow;
    }

    public void setPraiseShow(Integer praiseShow) {
        this.praiseShow = praiseShow;
    }
}
