package com.hero.zhaoq.emotionboardlib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * date:2017/11/23 / 14:42
 * zhaoqiang:zhaoq_hero@163.com
 */
public class EmotionsAdapter<T> extends BoardBaseAdapter {

    protected int mDelbtnPosition;

    public EmotionsAdapter(Context context, EmoticonPageEntity emoticonPageEntity, EmoticonClickListener onEmoticonClickListener) {
        super(context,emoticonPageEntity,onEmoticonClickListener);
        this.mDelbtnPosition = -1;
        checkDelBtn(emoticonPageEntity);
    }



    private void checkDelBtn(EmoticonPageEntity entity) {
        EmoticonPageEntity.DelBtnStatus delBtnStatus = entity.getDelBtnStatus();
        if (EmoticonPageEntity.DelBtnStatus.GONE.equals(delBtnStatus)) {
            return;
        }
        if (EmoticonPageEntity.DelBtnStatus.FOLLOW.equals(delBtnStatus)) {
            mDelbtnPosition = getCount();
            mData.add(null);
        } else if (EmoticonPageEntity.DelBtnStatus.LAST.equals(delBtnStatus)) {
            int max = entity.getLine() * entity.getRow();
            while (getCount() < max) {
                mData.add(null);
            }
            mDelbtnPosition = getCount() - 1;
        }
    }

    protected boolean isDelBtn(int position) {
        return position == mDelbtnPosition;
    }

    @Override
    protected void bindView(int position, ViewHolder viewHolder, ViewGroup parent) {
        final boolean isDelBtn = isDelBtn(position);
        final EmoticonEntity emoticonEntity = (EmoticonEntity) mData.get(position);
        if (isDelBtn) {
            viewHolder.iv_emoticon.setImageResource(R.mipmap.icon_del);
            viewHolder.iv_emoticon.setBackgroundResource(R.drawable.bg_emoticon);
        } else {
            if (emoticonEntity != null) {
                try {
                    ImageLoader.getInstance(viewHolder.iv_emoticon.getContext()).displayImage(emoticonEntity.getIconUri(),
                            viewHolder.iv_emoticon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewHolder.iv_emoticon.setBackgroundResource(R.drawable.bg_emoticon);
            }
        }
        viewHolder.iv_emoticon.setLayoutParams(new LinearLayout.LayoutParams(
                mContext.getResources().getDimensionPixelOffset(R.dimen.item_emoticon_size_default),
                mContext.getResources().getDimensionPixelOffset(R.dimen.item_emoticon_size_default)));
        viewHolder.iv_emoticon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        viewHolder.title.setVisibility(View.GONE);

        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEmoticonClickListener != null) {
                    mOnEmoticonClickListener.onEmoticonClick(emoticonEntity, Constants.EMOTICON_CLICK_TEXT, isDelBtn);
                }
            }
        });
    }

}
