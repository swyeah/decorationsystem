package com.sise.po;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:55 2018/2/24
 */
public class MaterialOffer implements Serializable {
    private static final long serialVersionUID = 346357563165833491L;
    private Integer offerId;
    @NotNull(message = "供应商名字不能为空")
    @Size(max = 100,min = 1,message = "供应商名字长度必须在1-100之间")
    private String offerName;

    @NotNull(message = "供应商电话号码不能为空")
    @Size(min = 11,max = 11,message = "供应商电话号码长度为11")
    private String offerPhone;
    @NotNull(message = "供应商地址不能为空")
    @Size(min = 1,max = 100,message = "供应商地址长度必须在1-100之间")
    private String offerAddress;

    public MaterialOffer() {
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferPhone() {
        return offerPhone;
    }

    public void setOfferPhone(String offerPhone) {
        this.offerPhone = offerPhone;
    }

    public String getOfferAddress() {
        return offerAddress;
    }

    public void setOfferAddress(String offerAddress) {
        this.offerAddress = offerAddress;
    }

    @Override
    public String toString() {
        return "MaterialOffer{" +
                "offerId=" + offerId +
                ", offerName='" + offerName + '\'' +
                ", offerPhone='" + offerPhone + '\'' +
                ", offerAddress='" + offerAddress + '\'' +
                '}';
    }
}
