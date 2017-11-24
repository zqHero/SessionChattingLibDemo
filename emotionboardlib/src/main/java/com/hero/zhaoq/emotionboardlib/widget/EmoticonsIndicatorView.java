package com.hero.zhaoq.emotionboardlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hero.zhaoq.emotionboardlib.EmoticonsInputBoardUtils;
import com.hero.zhaoq.emotionboardlib.R;
import com.hero.zhaoq.emotionboardlib.entity.PageSetEntity;
import com.hero.zhaoq.emotionboardlib.utils.DimenUtils;

import java.util.ArrayList;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 11:58
 * zhaoqiang:zhaoq_hero@163.com
 * 表情滑动的  指示器：
 */
public class EmoticonsIndicatorView extends LinearLayout {

    private static final int MARGIN_LEFT = 8;
    protected Context mContext;
    protected ArrayList<ImageView> mImageViews;
    protected Drawable mDrawableSelect;
    protected Drawable mDrawableNomal;
    protected LayoutParams mLeftLayoutParams;

    public EmoticonsIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.setOrientation(HORIZONTAL);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.EmoticonsIndicatorView, 0, 0);

        try {
            mDrawableSelect = a.getDrawable(R.styleable.EmoticonsIndicatorView_bmpSelect);
            mDrawableNomal = a.getDrawable(R.styleable.EmoticonsIndicatorView_bmpNomal);
        } finally {
            a.recycle();
        }

        if(mDrawableNomal == null) {
            mDrawableNomal = getResources().getDrawable(R.drawable.indicator_point_nomal);
        }
        if(mDrawableSelect == null) {
            mDrawableSelect = getResources().getDrawable(R.drawable.indicator_point_select);
        }

        mLeftLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLeftLayoutParams.leftMargin = DimenUtils.dip2px(context, MARGIN_LEFT);
    }

    public void playTo(int position, PageSetEntity pageSetEntity) {
        if (!checkPageSetEntity(pageSetEntity)) {
            return;
        }
        updateIndicatorCount(pageSetEntity.getPageCount());
        for (ImageView iv : mImageViews) {
            iv.setImageDrawable(mDrawableNomal);
        }
        mImageViews.get(position).setImageDrawable(mDrawableSelect);
    }

    public void playBy(int startPosition, int nextPosition, PageSetEntity pageSetEntity) {
        if (!checkPageSetEntity(pageSetEntity)) {
            return;
        }
        updateIndicatorCount(pageSetEntity.getPageCount());
        if (startPosition < 0 || nextPosition < 0 || nextPosition == startPosition) {
            startPosition = nextPosition = 0;
        }
        if (startPosition < 0) {
            startPosition = nextPosition = 0;
        }
        final ImageView imageViewStrat = mImageViews.get(startPosition);
        final ImageView imageViewNext = mImageViews.get(nextPosition);
        imageViewStrat.setImageDrawable(mDrawableNomal);
        imageViewNext.setImageDrawable(mDrawableSelect);
    }

    protected boolean checkPageSetEntity(PageSetEntity pageSetEntity) {
        if (pageSetEntity != null && pageSetEntity.isShowIndicator()) {
            setVisibility(VISIBLE);
            return true;
        } else {
            setVisibility(GONE);
            return false;
        }
    }

    protected void updateIndicatorCount(int count) {
        if (mImageViews == null) {
            mImageViews = new ArrayList<>();
        }
        if (count > mImageViews.size()) {
            for (int i = mImageViews.size(); i < count; i++) {
                ImageView imageView = new ImageView(mContext);
                imageView.setImageDrawable(i == 0 ? mDrawableSelect : mDrawableNomal);
                this.addView(imageView, mLeftLayoutParams);
                mImageViews.add(imageView);
            }
        }
        for (int i = 0; i < mImageViews.size(); i++) {
            if (i >= count) {
                mImageViews.get(i).setVisibility(GONE);
            } else {
                mImageViews.get(i).setVisibility(VISIBLE);
            }
        }
    }
}
