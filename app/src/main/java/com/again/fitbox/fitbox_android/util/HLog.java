package com.again.fitbox.fitbox_android.util;

import android.util.Log;

/**
 * Created by jeong on 2016. 11. 10..
 */

public class HLog {


    public static boolean isDebugMode = true;
    public static final void e(String TAG,String CLASS,String msg){
        if(isDebugMode){
            String THREAD = Thread.currentThread().getName();
            String text = "[" + THREAD + "] " + CLASS + " " + msg;
            Log.e(TAG,text);
        }
    }

    public static final void w(String TAG, String CLASS, String msg) {
        if(isDebugMode) {
            String THREAD = Thread.currentThread().getName();
            String text = "[" + THREAD + "] " + CLASS + " " + msg;
            Log.w(TAG, text);
        }
    }

    public static final void i(String TAG, String CLASS, String msg) {
        if(isDebugMode) {
            String THREAD = Thread.currentThread().getName();
            String text = "[" + THREAD + "] " + CLASS + " " + msg;
            Log.i(TAG, text);
        }
    }

    public static final void d(String TAG, String CLASS, String msg) {
        if(isDebugMode) {
            String THREAD = Thread.currentThread().getName();
            String text = "[" + THREAD + "] " + CLASS + " " + msg;
            Log.d(TAG, text);
        }
    }

    public static final void v(String TAG, String CLASS, String msg) {
        if(isDebugMode) {
            String THREAD = Thread.currentThread().getName();
            String text = "[" + THREAD + "] " + CLASS + " " + msg;
            Log.v(TAG, text);
        }
    }
}
