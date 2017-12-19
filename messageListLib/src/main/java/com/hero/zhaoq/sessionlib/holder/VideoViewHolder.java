package com.hero.zhaoq.sessionlib.holder;

import android.view.View;

import com.hero.zhaoq.sessionlib.beans.IMessage;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 10:10
 * zhaoqiang:zhaoq_hero@163.com
 */
public class VideoViewHolder<Message extends IMessage> extends BaseMessageViewHolder<Message>{

    public VideoViewHolder(View itemView, boolean isSender) {
        super(itemView);
    }

    @Override
    public void onBind(Message message) {

    }


}
