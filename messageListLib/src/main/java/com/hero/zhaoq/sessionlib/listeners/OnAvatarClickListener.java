package com.hero.zhaoq.sessionlib.listeners;

import com.hero.zhaoq.sessionlib.beans.IMessage;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 09:54
 * zhaoqiang:zhaoq_hero@163.com
 * 点击  头像：
 */
public interface OnAvatarClickListener<MESSAGE extends IMessage> {
    void onAvatarClick(MESSAGE message);
}
