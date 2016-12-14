package com.again.fitbox.fitbox_android.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.again.fitbox.fitbox_android.R;
import com.again.fitbox.fitbox_android.database.dao.BoardDataDao;
import com.again.fitbox.fitbox_android.model.Aticle;
import com.again.fitbox.fitbox_android.model.Board;
import com.again.fitbox.fitbox_android.presenter.MainListPresenter;
import com.again.fitbox.fitbox_android.presenterImpl.MainListPresenterImpl;
import com.again.fitbox.fitbox_android.util.SimpleDB;
import com.again.fitbox.fitbox_android.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainListActivity extends AppCompatActivity implements MainListPresenter {


    Context context = null;
    private MainListPresenterImpl mMainListPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlist);

        context = getApplicationContext();

        ArrayList<Board> boardList = prepareSimpleDB(context);

        Log.d("test","test");

        LinearLayout ll = (LinearLayout)findViewById(R.id.itemList);
        Log.d("keyset List ::::", boardList.toString());
        mMainListPresenterImpl = new MainListPresenterImpl(this);

        for(Board board : boardList ){
            Button button = new AppCompatButton(this);
            button.setText(board.getDate());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(),EditActivity.class);

                    String buttonText = (String)((Button)view).getText();

                    intent.putExtra("key",buttonText);
                    startActivity(intent);

                    //mMainListPresenterImpl.getToastText(text);

                }
            });
            ll.addView(button);
        }
    }

    @Override
    public void showToast(String text) {
        new ToastUtil(context).makeText(text);
    }

    private ArrayList<Board> prepareSimpleDB(Context context){


        ArrayList<Board> dataList = new ArrayList<Board>();
        BoardDataDao boardDataDao = new BoardDataDao(context);
        List<Board> boardDataList = boardDataDao.get();

        Log.d("boardDataList",boardDataList.toString());

        for(int i = 0; i<boardDataList.size(); i++ ){

            dataList.add(boardDataList.get(i));
        }

        return dataList;
    }
}
