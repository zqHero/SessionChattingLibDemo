package com.hero.zhaoq.emotionboardlib.entity;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 14:41
 * zhaoqiang:zhaoq_hero@163.com
 */
public class EmoticonBean {

    private long mEventType;
    private String mIconUri;
    private String mContent;

    public long getEventType() {
        return mEventType;
    }

    public void setEventType(long eventType) {
        this.mEventType = eventType;
    }

    public String getIconUri() {
        return mIconUri;
    }

    public void setIconUri(String iconUri) {
        this.mIconUri = iconUri;
    }

    public void setIconUri(int iconUri) {
        this.mIconUri = "" + iconUri;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public EmoticonBean(long eventType, String iconUri, String content) {
        this.mEventType = eventType;
        this.mIconUri = iconUri;
        this.mContent = content;
    }

    public EmoticonBean(String iconUri, String content) {
        this.mIconUri = iconUri;
        this.mContent = content;
    }

    public EmoticonBean(String content) {
        this.mContent = content;
    }

    public EmoticonBean() { }

    @Override
    public String toString() {
        return "EmoticonBean{" +
                "mEventType=" + mEventType +
                ", mIconUri='" + mIconUri + '\'' +
                ", mContent='" + mContent + '\'' +
                '}';
    }
}

