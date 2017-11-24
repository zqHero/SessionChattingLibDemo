package com.hero.zhaoq.emotionboardlib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hero.zhaoq.emotionboardlib.Constants;
import com.hero.zhaoq.emotionboardlib.R;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonBean;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageBean;
import com.hero.zhaoq.emotionboardlib.interfce.EmoticonClickListener;
import com.hero.zhaoq.emotionboardlib.utils.ImageLoader;

import java.io.IOException;

/**
 * author: zhaoqiang
 * date:2017/11/23 / 14:42
 * zhaoqiang:zhaoq_hero@163.com
 */
public class EmoticonsAdapter<T> extends BoardBaseAdapter {

    protected int mDelbtnPosition;

    public EmoticonsAdapter(Context context, EmoticonPageBean emoticonPageBean, EmoticonClickListener onEmoticonClickListener) {
        super(context, emoticonPageBean,onEmoticonClickListener);
        this.mDelbtnPosition = -1;
        checkDelBtn(emoticonPageBean);
    }

    private void checkDelBtn(EmoticonPageBean entity) {
        EmoticonPageBean.DelBtnStatus delBtnStatus = entity.getDelBtnStatus();
        if (EmoticonPageBean.DelBtnStatus.GONE.equals(delBtnStatus)) {
            return;
        }
        if (EmoticonPageBean.DelBtnStatus.FOLLOW.equals(delBtnStatus)) {
            mDelbtnPosition = getCount();
            mData.add(null);
        } else if (EmoticonPageBean.DelBtnStatus.LAST.equals(delBtnStatus)) {
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
        final EmoticonBean emoticonBean = (EmoticonBean) mData.get(position);
        if (isDelBtn) {
            viewHolder.iv_emoticon.setImageResource(R.mipmap.icon_del);
            viewHolder.iv_emoticon.setBackgroundResource(R.drawable.bg_emoticon);
        } else {
            if (emoticonBean != null) {
                try {
                    ImageLoader.getInstance(viewHolder.iv_emoticon.getContext()).displayImage(emoticonBean.getIconUri(),
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
                    mOnEmoticonClickListener.onEmoticonClick(emoticonBean, Constants.EMOTICON_CLICK_TEXT, isDelBtn);
                }
            }
        });
    }

}
