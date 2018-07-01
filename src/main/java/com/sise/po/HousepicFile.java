package com.sise.po;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:23 2018/2/20
 */
public class HousepicFile {

    private Integer fileId;

    private String fileName;

    private String fileUrl;

    private Integer housepicId;

    public HousepicFile() {
    }

    public HousepicFile(Integer fileId, String fileName, String fileUrl, Integer housepicId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.housepicId = housepicId;
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

    public Integer getHousepicId() {
        return housepicId;
    }

    public void setHousepicId(Integer housepicId) {
        this.housepicId = housepicId;
    }

    @Override
    public String toString() {
        return "HousepicFile{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", housepicId=" + housepicId +
                '}';
    }
}
