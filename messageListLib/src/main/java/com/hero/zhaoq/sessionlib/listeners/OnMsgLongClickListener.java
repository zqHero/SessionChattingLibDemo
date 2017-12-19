package com.hero.zhaoq.sessionlib.listeners;

import com.hero.zhaoq.sessionlib.beans.IMessage;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 09:52
 * zhaoqiang:zhaoq_hero@163.com
 */
public interface OnMsgLongClickListener<MESSAGE extends IMessage> {
    void onMessageLongClick(MESSAGE message);
}
