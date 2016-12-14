package com.again.fitbox.fitbox_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.again.fitbox.fitbox_android.model.Board;

import java.util.ArrayList;

/**
 * Created by jeong on 2016. 12. 12..
 */

public class BoardListAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Board> arrayBoard = new ArrayList<Board>();

    public BoardListAdapter(Context context) {
        mContext = context;

    }

    public void addItem(Board board){
        arrayBoard.add(board);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {




        return null;
    }


}
