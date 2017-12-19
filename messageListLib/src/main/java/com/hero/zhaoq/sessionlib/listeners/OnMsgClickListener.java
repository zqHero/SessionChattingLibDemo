package com.hero.zhaoq.sessionlib.listeners;

import com.hero.zhaoq.sessionlib.beans.IMessage;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 09:54
 * zhaoqiang:zhaoq_hero@163.com
 */

public interface OnMsgClickListener<MESSAGE extends IMessage> {
    void onMessageClick(MESSAGE message);
}

