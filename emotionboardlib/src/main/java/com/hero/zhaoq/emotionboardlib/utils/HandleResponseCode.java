package com.hero.zhaoq.emotionboardlib.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 13:53
 * zhaoqiang:zhaoq_hero@163.com
 * handle status code:
 */
public class HandleResponseCode {

    //处理  状态码：
    public static void onHandle(Context context, int status, boolean isCenter){
        Toast toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(IdHelper.getLayout(context, "default_toast"), null);
        TextView content =  view.findViewById(IdHelper.getViewID(context, "jmui_toast_content_tv"));
        switch (status){
            case 0:
                break;
            case 1000:
                content.setText(IdHelper.getString(context, "premission_no_defined_toast"));
                break;
            case 1001:
                content.setText(IdHelper.getString(context, "picture_not_found_toast"));
                break;
            case 1002:
                content.setText(IdHelper.getString(context, "user_already_exist_toast"));
                break;
            //TODO  you can defined you self response code：

            default:
                content.setText("Response code: " + status);
                break;
        }
        if(isCenter){
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        view.getBackground().setAlpha(150);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}

