package com.hero.zhaoq.emotionboardlib.interfce;

import android.view.ViewGroup;

import com.hero.zhaoq.emotionboardlib.adapter.EmoticonsAdapter;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 15:02
 * zhaoqiang:zhaoq_hero@163.com
 */

public interface EmoticonDisplayListener <T> {

    void onBindView(int position, ViewGroup parent, EmoticonsAdapter.ViewHolder viewHolder, T t, boolean isDelBtn);
}
