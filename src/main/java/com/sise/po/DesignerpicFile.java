package com.sise.po;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:04 2018/2/22
 */
public class DesignerpicFile {

    private Integer fileId;

    private String fileName;

    private String fileDescribe;

    private String fileUrl;

    private Integer designerpicId;

    public DesignerpicFile() {
    }

    public DesignerpicFile(String fileName, String fileDescribe, String fileUrl, Integer designerpicId) {
        this.fileName = fileName;
        this.fileDescribe = fileDescribe;
        this.fileUrl = fileUrl;
        this.designerpicId = designerpicId;
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

    public String getFileDescribe() {
        return fileDescribe;
    }

    public void setFileDescribe(String fileDescribe) {
        this.fileDescribe = fileDescribe;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getDesignerpicId() {
        return designerpicId;
    }

    public void setDesignerpicId(Integer designerpicId) {
        this.designerpicId = designerpicId;
    }

    @Override
    public String toString() {
        return "DesignerpicFile{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileDescribe='" + fileDescribe + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", designerpicId=" + designerpicId +
                '}';
    }
}
