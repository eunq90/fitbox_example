package com.again.fitbox.fitbox_android.database.table;

import java.util.ArrayList;

/**
 * Created by jeong on 2016. 12. 9..
 */

public class BoardContentTable {

    public static final String TABLE_NAME = "TB_BOARDCONTENT";
    public static final Integer TABLE_VERSION = 1;


    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_BDSEQ = "BD_ID";                 //BOARD._ID
    public static final String COLUMN_CONTENTTYPE = "BC_CONTNETTYPE";  //글의 타입 (위젯 타입)
    public static final String COLUMN_INDATE = "BC_INDATE";
    public static final String COLUMN_CONTENT = "BC_CONTENT";
    public static final String COLUMN_DELETE = "BC_DELETE";

    /* Table Creation DDL */
    public static final String TABLE_CREATE_DATATABLE = "CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BDSEQ +" INTEGER, "
            + COLUMN_CONTENTTYPE + " TEXT, "
            + COLUMN_INDATE + " TEXT, "
            + COLUMN_CONTENT + " TEXT, "
            + COLUMN_DELETE +" INTEGER ); ";

    /* Index Create DDL */
    public static final String INDEX_CREATE_DATATABLE = "CREATE UNIQUE INDEX "
            + TABLE_NAME + "_pk ON "
            + TABLE_NAME + " (" +  COLUMN_ID + " );";



    public static String[] getColumnNames(){
        String[] columnNames = {COLUMN_BDSEQ,COLUMN_CONTENTTYPE,COLUMN_INDATE,COLUMN_CONTENT,COLUMN_DELETE};
        return columnNames;
    }

    public static ArrayList<String> InitDataInsert(){


        String[][] initValue = {
                {"1번글","1번글내용"},
                {"2번글","2번글내용"},
                {"3번글","3번글내용"},
                {"4번글","4번글내용"},
                {"5번글","5번글내용"},
                {"6번글","6번글내용"},
                {"7번글","7번글내용"},
                {"8번글","8번글내용"},
                {"9번글","9번글내용"},
                {"10번글","10번글내용"}

        };

        String initData = "";
        ArrayList<String> initDataList = new ArrayList<String>();
        for(int i=0; i<initValue.length; i++) {
            initData = "INSERT INTO " + BoardTable.TABLE_NAME + "(";
            for(int j=0; j<getColumnNames().length; j++){
                initData += getColumnNames()[j] + ( j == getColumnNames().length-1 ? ")" : "," );
            }
            initData += " VALUES( ";
            for(int j=0; j<initValue[i].length; j++){
                initData += "'" + initValue[i][j] + ( j == initValue[i].length-1 ?  "');" : "',");
            }
            initDataList.add(initData);
        }

        return initDataList;
    }


}
