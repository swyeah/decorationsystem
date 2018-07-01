package com.sise.po;

import java.io.Serializable;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:08 2018/2/28
 */
public class DesignerShowpic implements Serializable {

    private static final long serialVersionUID = 363723663108775383L;
    private Integer fileId;

    private String fileUrl;

    private Integer showId;

    public DesignerShowpic() {
    }

    public DesignerShowpic(String fileUrl , Integer showId) {
        this.fileUrl = fileUrl;

        this.showId = showId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }


    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }
}
