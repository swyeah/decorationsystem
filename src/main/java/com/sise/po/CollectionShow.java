package com.sise.po;

import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:14 2018/2/28
 */
public class CollectionShow implements Serializable {
    private static final long serialVersionUID = -4288887450967530313L;

    private Integer collectionId;

    private Integer collectionMan;

    private Integer collectionShow;

    public CollectionShow() {
    }

    public CollectionShow(Integer collectionMan, Integer collectionShow) {
        this.collectionMan = collectionMan;
        this.collectionShow = collectionShow;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getCollectionMan() {
        return collectionMan;
    }

    public void setCollectionMan(Integer collectionMan) {
        this.collectionMan = collectionMan;
    }

    public Integer getCollectionShow() {
        return collectionShow;
    }

    public void setCollectionShow(Integer collectionShow) {
        this.collectionShow = collectionShow;
    }
}
