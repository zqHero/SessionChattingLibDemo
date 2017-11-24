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

