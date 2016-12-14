package com.again.fitbox.fitbox_android.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.again.fitbox.fitbox_android.R;
import com.again.fitbox.fitbox_android.presenter.TabNaviCompBasePresenter;

/**
 * Created by jeong on 2016. 11. 12..
 */

public class TabNaviComplaintBase extends LinearLayout implements TabNaviCompBasePresenter{

    Context context;

    public TabNaviComplaintBase(Context context, AttributeSet attrs) {

        super(context, attrs);

        this.context = context;


        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.tab_navi_complatint, this, true);

    }


    @Override
    public void showToast(String text) {

    }
}