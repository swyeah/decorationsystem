package com.sise.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 13:09 2018/3/7
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -182256234864298123L;

    private Integer msgId;

    private String toName;

    private Integer fromId;

    private String fromName;

    private Date sendDate;

    private String msgContent;

    private Integer postsId;

    private Integer toId;

    public Message() {
    }

    public Message(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = 1;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgId=" + msgId +
                ", toName='" + toName + '\'' +
                ", fromId=" + fromId +
                ", fromName=" + fromName +
                ", sendDate=" + sendDate +
                ", msgContent='" + msgContent + '\'' +
                ", postsId=" + postsId +
                ", toId=" + toId +
                '}';
    }
}
