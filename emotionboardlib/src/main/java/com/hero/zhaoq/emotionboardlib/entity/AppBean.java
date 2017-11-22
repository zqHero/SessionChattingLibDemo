package com.hero.zhaoq.emotionboardlib.entity;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 14:50
 * zhaoqiang:zhaoq_hero@163.com
 */

public class AppBean {

    private int id;
    private int icon;
    private String funcName;

    public int getIcon() {
        return icon;
    }

    public String getFuncName() {
        return funcName;
    }

    public int getId() {
        return id;
    }

    public AppBean(int icon, String funcName){
        this.icon = icon;
        this.funcName = funcName;
    }

}

