package com.sise.po;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 9:58 2018/2/22
 */
public class DecorationStyle {
    private Integer styleId;

    private String styleName;

    private String content;

    public DecorationStyle() {
    }

    public DecorationStyle(String styleName, String content) {
        this.styleName = styleName;
        this.content = content;
    }

    public DecorationStyle(Integer styleId) {
        this.styleId = styleId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DecorationStyle{" +
                "styleId=" + styleId +
                ", styleName='" + styleName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
