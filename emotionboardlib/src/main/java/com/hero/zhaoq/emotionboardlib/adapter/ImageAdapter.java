package com.hero.zhaoq.emotionboardlib.adapter;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hero.zhaoq.emotionboardlib.Constants;
import com.hero.zhaoq.emotionboardlib.R;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonBean;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageBean;
import com.hero.zhaoq.emotionboardlib.interfce.EmoticonClickListener;
import com.hero.zhaoq.emotionboardlib.utils.ImageLoader;

import java.io.File;
import java.io.IOException;

/**
 * author: zhaoqiang
 * date:2017/11/23 / 16:59
 * zhaoqiang:zhaoq_hero@163.com
 */
public class ImageAdapter<T> extends BoardBaseAdapter {

    public ImageAdapter(Context context, EmoticonPageBean emoticonPageBean,
                        EmoticonClickListener onEmoticonClickListener) {
        super(context, emoticonPageBean, onEmoticonClickListener);
    }

    @Override
    protected void bindView(int position, ViewHolder viewHolder, ViewGroup parent) {
        final EmoticonBean emoticonBean = (EmoticonBean) mData.get(position);
        if (emoticonBean == null) return;
        try {
            if (emoticonBean.getIconUri().endsWith(".png")) {
                ImageLoader.getInstance(viewHolder.iv_emoticon.getContext()).displayImage(emoticonBean.getIconUri(),
                        viewHolder.iv_emoticon);
            } else {
                Glide.with(viewHolder.iv_emoticon.getContext()).load(emoticonBean.getIconUri())
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.iv_emoticon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewHolder.iv_emoticon.setLayoutParams(new LinearLayout.LayoutParams(
                mContext.getResources().getDimensionPixelOffset(R.dimen.item_emoticon_image_size), mContext.getResources().getDimensionPixelOffset(R.dimen.item_emoticon_image_size)));
        viewHolder.iv_emoticon.setBackgroundResource(R.drawable.bg_emoticon);

        viewHolder.title.setVisibility(View.GONE);
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEmoticonClickListener != null) {
                    mOnEmoticonClickListener.onEmoticonClick(emoticonBean, Constants.EMOTICON_CLICK_IMAGE, false);
                }
            }
        });
    }
}
