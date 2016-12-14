package com.again.fitbox.fitbox_android.presenterImpl;

import com.again.fitbox.fitbox_android.presenter.MainListPresenter;

/**
 * Created by jeong on 2016. 11. 14..
 */

public class MainListPresenterImpl{


    private MainListPresenter mMainListPresenter;

    public MainListPresenterImpl(MainListPresenter mainListPresenter){
        this.mMainListPresenter = mainListPresenter;
    }

    public void attachView(MainListPresenter mainListPresenter){

        this.mMainListPresenter = mainListPresenter;

    }


    public void getToastText(String text){

        mMainListPresenter.showToast(text);

    }
}
