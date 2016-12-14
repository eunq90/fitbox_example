package com.again.fitbox.fitbox_android.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jeong on 2016. 11. 14..
 */

public class ToastUtil {


    private Context context = null;

    public ToastUtil(Context context){
        this.context = context;
    }

    public void makeText(String text){

        makeText(text,Toast.LENGTH_SHORT);

    }

    public void makeText(String text,int lengthType){

        if ( context != null ) {

            Toast.makeText(context, text, lengthType).show();

        }
    }



}
