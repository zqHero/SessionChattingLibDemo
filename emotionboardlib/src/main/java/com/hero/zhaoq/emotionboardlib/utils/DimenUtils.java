package com.hero.zhaoq.emotionboardlib.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * author: zhaoqiang
 * date:2017/11/24 / 14:35
 * zhaoqiang:zhaoq_hero@163.com
 */

public class DimenUtils {

    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static int getDisplayWidthPixels(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
