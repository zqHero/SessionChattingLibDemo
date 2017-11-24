package com.hero.zhaoq.emotionboardlib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.hero.zhaoq.emotionboardlib.EmoticonsInputBoardUtils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//        //属于  表情符号：
//        //获取  内容    处理表情图片信息：
//        SpannableStringBuilder builder = new SpannableStringBuilder(getText().toString());
//        Matcher m = Pattern.compile("\\[\\![^\\x00-\\xff]*\\!\\]").matcher(getText().toString());
//        if (m != null) {
//            while (m.find()) {
//                Drawable draw = EmojiDisplay.getDrawable(getContext(), "emoji_0x" +
//                        m.group().toString().substring(2, m.group().length() - 2));
//                if (draw != null) {
//                    draw.setBounds(0, 0,
//                            EmoticonsInputBoardUtils.getFontHeight(this),
//                            EmoticonsInputBoardUtils.getFontHeight(this));
//                    builder.setSpan(new MyImageSpan(draw), m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                }
//            }
//        }
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


    // 显示 位置
    class MyImageSpan extends ImageSpan {

        public MyImageSpan(Drawable drawable) {
            super(drawable);
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end,
                         float x, int top, int y, int bottom, Paint paint) {
//            Paint.FontMetricsInt fm = paint.getFontMetricsInt();
            Drawable drawable = getDrawable();
//            Log.i("info", "measureHeight:" + getMeasuredHeight() + "====" +
//                 "===start:" + start + "===end:" + end + "====x：" +
//                    x + "====y:" + y + "=======top:" + top + "======bottom:" + bottom +
//                    "=====descent :" + fm.descent + "=====ascent :" + fm.ascent +"====drawablebottom:"+
//                    drawable.getBounds().bottom  + "=========" + fm.leading);
//            int transY = (y + fm.descent + y + fm.ascent) / 2
//                    - drawable.getBounds().bottom / 2;
            int transY  = ((bottom-top) - drawable.getBounds().bottom)/2+top;
            canvas.save();
            canvas.translate(x, transY);
            drawable.draw(canvas);
            canvas.restore();
        }
    }
}
