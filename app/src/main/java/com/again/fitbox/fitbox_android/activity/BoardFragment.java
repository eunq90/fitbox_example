package com.again.fitbox.fitbox_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.again.fitbox.fitbox_android.R;

/**
 * Created by jeong on 2016. 12. 12..
 */

public class BoardFragment extends Fragment {

    Context mContext;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_boardbutton,container,false);

        mContext = getContext();




        return rootView;

    }
}
