package com.again.fitbox.fitbox_android.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jeong on 2016. 11. 4..
 */

public class FitPreference {


    private final String PREF_NAME = "com.again.fitbox";
    public static final String MENU = "menu";
    public static final String IS_LOGGED_IN = "logIn";


    Context mContext;

    public FitPreference(Context mContext) {
        this.mContext = mContext;
    }

    public void put(String key, int value){
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key,value);
        editor.commit();


    }
}
