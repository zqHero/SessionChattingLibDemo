package com.hero.zhaoq.sessionlib.holder;

import android.view.View;

import com.hero.zhaoq.sessionlib.beans.IMessage;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 10:14
 * zhaoqiang:zhaoq_hero@163.com
 */

public class EventViewHolder<MESSAGE extends IMessage>
        extends BaseMessageViewHolder<MESSAGE> {

    public EventViewHolder(View itemView, boolean isSender) {
        super(itemView);
    }

    @Override
    public void onBind(MESSAGE message) {

    }
}
