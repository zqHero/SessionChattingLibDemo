package com.hero.zhaoq.emotionboardlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;
import com.hero.zhaoq.emotionboardlib.R;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageBean;
import com.hero.zhaoq.emotionboardlib.interfce.EmoticonClickListener;

import java.util.ArrayList;

/**
 * author: zhaoqiang
 * date:2017/11/23 / 17:35
 * zhaoqiang:zhaoq_hero@163.com
 * <p>
 * base adapter class
 */
public abstract class BoardBaseAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected ArrayList<T> mData = new ArrayList<>();
    protected EmoticonPageBean mEmoticonPageBean;
    protected EmoticonClickListener mOnEmoticonClickListener;

    public BoardBaseAdapter(Context context, EmoticonPageBean emoticonPageBean, EmoticonClickListener onEmoticonClickListener) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData.addAll(emoticonPageBean.getEmoticonList());
        this.mEmoticonPageBean = emoticonPageBean;
        this.mOnEmoticonClickListener = onEmoticonClickListener;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //TODO :

//        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.m_item_emotion_view, null);

            viewHolder.rootView = convertView;
            viewHolder.iv_emoticon = convertView.findViewById(R.id.iv_emoticon);
            viewHolder.title = convertView.findViewById(R.id.title);

            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        int realItemHeight = ((View) parent.getParent()).getMeasuredHeight() / mEmoticonPageBean.getLine();
        viewHolder.rootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, realItemHeight));

        bindView(position, viewHolder, parent);

        return convertView;
    }

    protected abstract void bindView(int position, ViewHolder viewHolder, ViewGroup parent);

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_emoticon;
        public TextView title;
    }
}
