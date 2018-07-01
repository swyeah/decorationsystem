package com.sise.po;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:56 2018/2/23
 */
public class DecorationMode {

    private Integer modeId;

    private String modeName;

    private String modeDescribe;

    private Integer contractId;

    public DecorationMode() {
    }


    public DecorationMode(Integer modeId) {
        this.modeId = modeId;
    }

    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getModeDescribe() {
        return modeDescribe;
    }

    public void setModeDescribe(String modeDescribe) {
        this.modeDescribe = modeDescribe;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "DecorationMode{" +
                "modeId=" + modeId +
                ", modeName='" + modeName + '\'' +
                ", modeDescribe='" + modeDescribe + '\'' +
                ", contractId=" + contractId +
                '}';
    }
}
