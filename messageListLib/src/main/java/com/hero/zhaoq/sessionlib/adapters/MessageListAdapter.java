package com.hero.zhaoq.sessionlib.adapters;

import com.hero.zhaoq.sessionlib.beans.IMessage;
import com.hero.zhaoq.sessionlib.holder.HolderConfig;
import com.hero.zhaoq.sessionlib.holder.ViewHolder;
import com.hero.zhaoq.sessionlib.listeners.ImageLoader;
import com.hero.zhaoq.sessionlib.listeners.ScrollMoreListener;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 09:17
 * zhaoqiang:zhaoq_hero@163.com
 */

public class MessageListAdapter<MESSAGE extends IMessage>
        extends RecyclerView.Adapter<ViewHolder>
        implements ScrollMoreListener.OnLoadMoreListener {

    private boolean mScroll;
    private MediaPlayer mMediaPlayer;
    private Context mContext;
    private String mSenderId;
    private HolderConfig mHolders;
    private ScrollMoreListener.OnLoadMoreListener mListener;

    private ImageLoader mImageLoader;

    private List<Wrapper> mItems;

    public MessageListAdapter(String senderId, HolderConfig holders, ImageLoader imageLoader) {
        mMediaPlayer = new MediaPlayer();
        mSenderId = senderId;
        mHolders = holders;
        mImageLoader = imageLoader;
        mItems = new ArrayList<>();
    }

    private class Wrapper<DATA> {
        private DATA item;
        boolean isSelected;

        Wrapper(DATA item) {
            this.item = item;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public void setScrolling(boolean scroll) {
        this.mScroll = scroll;
    }

    public boolean getScrolling() {
        return this.mScroll;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onLoadMore(int page, int total) {

    }



}
