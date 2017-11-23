package com.hero.zhaoq.emotionboardlib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 11:25
 * zhaoqiang:zhaoq_hero@163.com
 */

public class EmoticonsEditText extends RichEditText {


    public EmoticonsEditText(Context context) {
        this(context, null);
    }

    public EmoticonsEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmoticonsEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } catch (ArrayIndexOutOfBoundsException e) {
            setText(getText().toString());
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(oldh > 0 && onSizeChangedListener != null){
            onSizeChangedListener.onSizeChanged(w, h, oldw, oldh);
        }
    }

    @Override
    public void setGravity(int gravity) {
        try {
            super.setGravity(gravity);
        } catch (ArrayIndexOutOfBoundsException e) {
            setText(getText().toString());
            super.setGravity(gravity);
        }
    }

    @Override
    public void setText(CharSequence text, TextView.BufferType type) {
        try {
            super.setText(text, type);
        } catch (ArrayIndexOutOfBoundsException e) {
            setText(text.toString());
        }
    }

    @Override
    protected final void onTextChanged(CharSequence arg0, int start, int lengthBefore, int after) {
        super.onTextChanged(arg0, start, lengthBefore, after);

    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if(onBackKeyClickListener != null){
            onBackKeyClickListener.onBackKeyClick();
        }
        return super.dispatchKeyEventPreIme(event);
    }



    public interface OnBackKeyClickListener {
        void onBackKeyClick();
    }

    OnBackKeyClickListener onBackKeyClickListener;

    public void setOnBackKeyClickListener(OnBackKeyClickListener i) {
        onBackKeyClickListener = i;
    }

    public interface OnSizeChangedListener {
        void onSizeChanged(int w, int h, int oldw, int oldh);
    }

    OnSizeChangedListener onSizeChangedListener;

    public void setOnSizeChangedListener(OnSizeChangedListener i) {
        onSizeChangedListener = i;
    }
}
