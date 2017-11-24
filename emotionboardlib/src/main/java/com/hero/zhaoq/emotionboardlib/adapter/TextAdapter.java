package com.hero.zhaoq.emotionboardlib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hero.zhaoq.emotionboardlib.Constants;
import com.hero.zhaoq.emotionboardlib.R;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonBean;
import com.hero.zhaoq.emotionboardlib.interfce.EmoticonClickListener;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageBean;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 15:13
 * zhaoqiang:zhaoq_hero@163.com
 *
 * 颜文字  适配器：
 */
public class TextAdapter extends BoardBaseAdapter {

    public TextAdapter(Context context, EmoticonPageBean emoticonPageBean, EmoticonClickListener onEmoticonClickListener) {
        super(context, emoticonPageBean, onEmoticonClickListener);
    }

    @Override
    protected void bindView(int position, ViewHolder viewHolder, ViewGroup parent) {
        final EmoticonBean emoticonBean = (EmoticonBean) mData.get(position);
        if (emoticonBean == null) return;
        viewHolder.iv_emoticon.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        viewHolder.iv_emoticon.setVisibility(View.GONE);

        viewHolder.title.setText(emoticonBean.getContent());
        viewHolder.title.setTextSize(mContext.getResources().getDimensionPixelSize(R.dimen.emotion_text_size));

        viewHolder.iv_emoticon.setBackgroundResource(R.drawable.bg_emoticon);

        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEmoticonClickListener != null) {
                    mOnEmoticonClickListener.onEmoticonClick(emoticonBean, Constants.EMOTICON_CLICK_TEXT, false);
                }
            }
        });
    }

}