package com.again.fitbox.fitbox_android.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.again.fitbox.fitbox_android.database.Constants;
import com.again.fitbox.fitbox_android.database.DatabaseHelper;
import com.again.fitbox.fitbox_android.database.table.BoardTable;
import com.again.fitbox.fitbox_android.model.Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jeong on 2016. 11. 28..
 */

public class BoardDataDao{


    private static final String CLASSNAME = BoardDataDao.class.getSimpleName();
    private DatabaseHelper db;

    public BoardDataDao(Context context){
        db = DatabaseHelper.getInstance(context);
    }

    public void close(){
        db.close();
    }


    public void insert(final Board to){

        ContentValues values = new ContentValues();

        values.put(BoardTable.COLUMN_DATE, to.getDate());

        Log.v(Constants.LOG_TAG,BoardDataDao.CLASSNAME+ " insert - title : "+ to.getDate());

        long rowId = db.insert(BoardTable.TABLE_NAME,values);
        if(rowId < 0 ){

            throw new SQLException("Fail At Insert");

        }

    }

    public void update(final Board to){
        ContentValues values = new ContentValues();

        values.put(BoardTable.COLUMN_DATE,to.getDate());

        Log.v(Constants.LOG_TAG,BoardDataDao.CLASSNAME + " update - _id : "+ String.valueOf(to.getId()));
        db.update(BoardTable.TABLE_NAME, values,to.getId());

    }


    public Board getBoard(String key){

        Cursor c = null;
        Board board = null;
        String sql = "SELECT * FROM " + BoardTable.TABLE_NAME + " WHERE " +BoardTable.COLUMN_DATE + " = "+ key;

        try {
            Log.d(Constants.LOG_TAG,BoardDataDao.CLASSNAME + " get - All ");
            c = db.get(sql);
            Log.d(" boardData List : " ,c.toString());

            c.moveToFirst();
            board = new Board();
            board.setId(c.getInt(c.getColumnIndex(BoardTable.COLUMN_ID)));
            board.setDate(c.getString(c.getColumnIndex(BoardTable.COLUMN_DATE)));
            board.setInDate(c.getString(c.getColumnIndex(BoardTable.COLUMN_INDATE)));
            board.setMoDate(c.getString(c.getColumnIndex(BoardTable.COLUMN_MODATE)));
            board.setDelete(c.getString(c.getColumnIndex(BoardTable.COLUMN_DELETE)));



        }catch (SQLException e){
            Log.e(Constants.LOG_TAG,BoardDataDao.CLASSNAME + " getList ",e);

        }finally {
            if ( c != null && !c.isClosed()){
                c.close();
            }
        }


        return board;
    }

    public List<Board> get(){

        Cursor c = null;
        ArrayList<Board> ret = null;

        String sql = "SELECT * FROM " + BoardTable.TABLE_NAME + " ORDER BY "+BoardTable.COLUMN_DATE + " DESC";
        Log.d("boardData List",sql);
        try {
            Log.d(Constants.LOG_TAG,BoardDataDao.CLASSNAME + " get - All ");
            c = db.get(sql);
            Log.d(" boardData List : " ,c.toString());
            ret = setBindCursor(c);


        }catch (SQLException e){
            Log.e(Constants.LOG_TAG,BoardDataDao.CLASSNAME + " getList ",e);

        }finally {
            if ( c != null && !c.isClosed()){
                c.close();
            }
        }

        return ret;

    }


    private ArrayList<Board> setBindCursor(final Cursor c) {

        ArrayList<Board> ret = new ArrayList<Board>();

        int numRows = c.getCount();

        c.moveToFirst();

        for(int i=0; i<numRows; i++ ){
            Board to = new Board();
            to.setId(c.getInt(c.getColumnIndex(BoardTable.COLUMN_ID)));
            to.setDate(c.getString(c.getColumnIndex(BoardTable.COLUMN_DATE)));
            to.setInDate(c.getString(c.getColumnIndex(BoardTable.COLUMN_INDATE)));
            to.setMoDate(c.getString(c.getColumnIndex(BoardTable.COLUMN_MODATE)));
            to.setDelete(c.getString(c.getColumnIndex(BoardTable.COLUMN_DELETE)));
            ret.add(to);
            c.moveToNext();

        }

        return ret;

    }

    public void logListInfo(List<Board> to){
        Log.i(Constants.LOG_TAG, "*** List begin *** " + "Results:" + to.size());

        Iterator<Board> itr = to.iterator();
        while(itr.hasNext()){
            String msg = ((Board)itr.next()).toString();
            Log.i(Constants.LOG_TAG, "DATAS: " + msg );
        }
        Log.i(Constants.LOG_TAG,"*** List End ****");

    }

}
