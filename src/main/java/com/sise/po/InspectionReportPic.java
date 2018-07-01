package com.sise.po;

import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 12:17 2018/3/5
 */
public class InspectionReportPic implements Serializable {
    private static final long serialVersionUID = 8511352870337912801L;

    private Integer fileId;

    private String fileName;

    private String fileUrl;

    private Integer reportId;

    public InspectionReportPic() {
    }

    @Override
    public String toString() {
        return "InspectionReportPic{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", reportId=" + reportId +
                '}';
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

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }
}
