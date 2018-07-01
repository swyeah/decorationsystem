package com.sise.po;

import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:49 2018/2/24
 */
public class MaterialFile implements Serializable{

    private static final long serialVersionUID = 1102894860767545408L;
    private Integer fileId;

    private String fileName;

    private String fileUrl;

    private Integer materialId;

    public MaterialFile() {
    }

    public MaterialFile(String fileName, String fileUrl, Integer materialId) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.materialId = materialId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    @Override
    public String toString() {
        return "MaterialFile{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", materialId=" + materialId +
                '}';
    }
}
