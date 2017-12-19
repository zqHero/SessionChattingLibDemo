package com.hero.zhaoq.sessionlib.holder;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

import com.hero.zhaoq.sessionlib.adapters.MessageListAdapter;
import com.hero.zhaoq.sessionlib.beans.IMessage;
import com.hero.zhaoq.sessionlib.listeners.ImageLoader;
import com.hero.zhaoq.sessionlib.listeners.OnAvatarClickListener;
import com.hero.zhaoq.sessionlib.listeners.OnMsgClickListener;
import com.hero.zhaoq.sessionlib.listeners.OnMsgLongClickListener;
import com.hero.zhaoq.sessionlib.listeners.OnMsgStatusViewClickListener;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 09:48
 * zhaoqiang:zhaoq_hero@163.com
 */

public abstract class BaseMessageViewHolder<MESSAGE extends IMessage>
        extends ViewHolder<MESSAGE> {

    protected Context mContext;

    protected float mDensity;
    protected int mPosition;
    protected boolean mIsSelected;
    protected ImageLoader mImageLoader;

    protected OnMsgLongClickListener<MESSAGE> mMsgLongClickListener;
    protected OnMsgClickListener<MESSAGE> mMsgClickListener;
    protected OnAvatarClickListener<MESSAGE> mAvatarClickListener;
    protected OnMsgStatusViewClickListener<MESSAGE> mMsgStatusViewClickListener;

    public BaseMessageViewHolder(View itemView) {
        super(itemView);
    }

    protected MediaPlayer mMediaPlayer;
    protected boolean mScroll;

    public boolean isSelected() {
        return mIsSelected;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
