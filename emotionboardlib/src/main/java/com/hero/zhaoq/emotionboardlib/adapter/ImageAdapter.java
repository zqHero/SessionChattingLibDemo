package com.hero.zhaoq.emotionboardlib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hero.zhaoq.emotionboardlib.Constants;
import com.hero.zhaoq.emotionboardlib.R;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonEntity;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageEntity;
import com.hero.zhaoq.emotionboardlib.interfce.EmoticonClickListener;
import com.hero.zhaoq.emotionboardlib.utils.ImageLoader;

import java.io.IOException;

/**
 * author: zhaoqiang
 * date:2017/11/23 / 16:59
 * zhaoqiang:zhaoq_hero@163.com
 */
public class ImageAdapter<T> extends BoardBaseAdapter {

    public ImageAdapter(Context context, EmoticonPageEntity emoticonPageEntity,
                        EmoticonClickListener onEmoticonClickListener) {
        super(context, emoticonPageEntity, onEmoticonClickListener);
    }


    @Override
    protected void bindView(int position, ViewHolder viewHolder, ViewGroup parent) {
        final EmoticonEntity emoticonEntity = (EmoticonEntity) mData.get(position);
        if (emoticonEntity == null) return;
        try {
            ImageLoader.getInstance(viewHolder.iv_emoticon.getContext()).displayImage(emoticonEntity.getIconUri(),
                    viewHolder.iv_emoticon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewHolder.iv_emoticon.setLayoutParams(new LinearLayout.LayoutParams(
                mContext.getResources().getDimensionPixelOffset(R.dimen.item_emoticon_image_size),
                mContext.getResources().getDimensionPixelOffset(R.dimen.item_emoticon_image_size)));
        viewHolder.iv_emoticon.setBackgroundResource(R.drawable.bg_emoticon);
        viewHolder.title.setText(emoticonEntity.getContent());


        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEmoticonClickListener != null) {
                    mOnEmoticonClickListener.onEmoticonClick(emoticonEntity, Constants.EMOTICON_CLICK_IMAGE, false);
                }
            }
        });
    }
}
