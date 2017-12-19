package com.hero.zhaoq.sessionlib.holder;

import android.view.View;

import com.hero.zhaoq.sessionlib.beans.IMessage;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 10:01
 * zhaoqiang:zhaoq_hero@163.com
 */

public class TxtViewHolder<MESSAGE extends IMessage>
        extends BaseMessageViewHolder<MESSAGE>{

    public TxtViewHolder(View itemView,boolean isSender) {
        super(itemView);
    }

    @Override
    public void onBind(MESSAGE message) {

    }
}
