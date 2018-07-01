package com.sise.po;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 21:05 2018/2/23
 */
public class ContractFile {

    private Integer fileId;

    private String fileName;

    private String fileUrl;

    private Integer contractId;

    public ContractFile() {
    }

    public ContractFile(Integer fileId, String fileName, String fileUrl) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
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

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "ContractFile{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
