package com.hero.zhaoq.emotionboardlib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hero.zhaoq.emotionboardlib.Constants;
import com.hero.zhaoq.emotionboardlib.R;
import com.hero.zhaoq.emotionboardlib.interfce.EmoticonClickListener;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonEntity;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageEntity;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 15:13
 * zhaoqiang:zhaoq_hero@163.com
 *
 * 颜文字  适配器：
 */
public class TextAdapter extends BoardBaseAdapter {

    public TextAdapter(Context context, EmoticonPageEntity emoticonPageEntity, EmoticonClickListener onEmoticonClickListener) {
        super(context, emoticonPageEntity, onEmoticonClickListener);
    }

    @Override
    protected void bindView(int position, ViewHolder viewHolder, ViewGroup parent) {
        final EmoticonEntity emoticonEntity = (EmoticonEntity) mData.get(position);
        if (emoticonEntity == null) return;
        viewHolder.iv_emoticon.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        viewHolder.iv_emoticon.setVisibility(View.GONE);
        viewHolder.title.setText(emoticonEntity.getContent());
        viewHolder.title.setTextSize(mContext.getResources().getDimensionPixelSize(R.dimen.emotion_text_size));
        viewHolder.iv_emoticon.setBackgroundResource(R.drawable.bg_emoticon);

        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEmoticonClickListener != null) {
                    mOnEmoticonClickListener.onEmoticonClick(emoticonEntity, Constants.EMOTICON_CLICK_TEXT, false);
                }
            }
        });
    }

}