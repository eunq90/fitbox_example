package com.again.fitbox.fitbox_android.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by jeong on 2016. 12. 12..
 */

public class CustomViewUtils {


    public static Integer getScreenWidth(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static Integer getScreenHeight(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;

    }
}
