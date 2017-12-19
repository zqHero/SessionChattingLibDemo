package com.hero.zhaoq.sessionlib.listeners;

import com.hero.zhaoq.sessionlib.beans.IMessage;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 09:54
 * zhaoqiang:zhaoq_hero@163.com
 *
 * 点击了  消息状态： 发送失败发送成功   正在发送图标：
 */
public interface OnMsgStatusViewClickListener<MESSAGE extends IMessage> {
    void onStatusViewClick(MESSAGE message);
}