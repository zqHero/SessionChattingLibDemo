package com.hero.zhaoq.emotionboardlib.interfce;

import android.view.View;
import android.view.ViewGroup;

import com.hero.zhaoq.emotionboardlib.entity.PageEntity;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 11:18
 * zhaoqiang:zhaoq_hero@163.com
 */
public interface PageViewInstantiateListener <T extends PageEntity> {

    View instantiateItem(ViewGroup container, int position, T pageEntity);

}

