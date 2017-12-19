package com.hero.zhaoq.sessionlib.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 09:38
 * zhaoqiang:zhaoq_hero@163.com
 */
public abstract class ViewHolder<DATA> extends RecyclerView.ViewHolder {

    public abstract void onBind(DATA data);

    public ViewHolder(View itemView) {
        super(itemView);
    }
}

