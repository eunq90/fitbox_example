package com.again.fitbox.fitbox_android.database.table;

import com.again.fitbox.fitbox_android.util.DateUtil;

import java.util.ArrayList;

/**
 * Created by jeong on 2016. 12. 5..
 */

public class BoardTable {


    public static final String TABLE_NAME = "TB_BOARD";
    public static final int TABLE_VERSION = 1;

    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_DATE = "BD_DATE";
    public static final String COLUMN_INDATE = "BD_INDATE";
    public static final String COLUMN_MODATE = "BD_MODATE";
    public static final String COLUMN_DELETE = "BD_DELETE";


    /* Table Creation DDL */
    public static final String TABLE_CREATE_DATATABLE = "CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DATE +" TEXT, "
            + COLUMN_INDATE + " TEXT, "
            + COLUMN_MODATE + " TEXT, "
            + COLUMN_DELETE +" INTEGER ); ";

    /* Index Create DDL */
    public static final String INDEX_CREATE_DATATABLE = "CREATE UNIQUE INDEX "
            + TABLE_NAME + "_pk ON "
            + TABLE_NAME + " (" +  COLUMN_ID + " );";



    public static ArrayList<String> InitDataInsert(){

        DateUtil dateUtil = new DateUtil();


        String nowDate = dateUtil.getDateTime();

        String[][] initValue = {
                {"20161201",nowDate,"null","0"},
                {"20161202",nowDate,"null","0"},
                {"20161203",nowDate,"null","0"},
                {"20161204",nowDate,"null","0"},
                {"20161205",nowDate,"null","0"},
                {"20161206",nowDate,"null","0"},
                {"20161207",nowDate,"null","0"},
                {"20161208",nowDate,"null","0"},
                {"20161209",nowDate,"null","0"},
                {"20161210",nowDate,"null","0"}
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
                        if(initValue[i][j]=="null"){
                            initData += initValue[i][j] + (j == initValue[i].length - 1 ? ");" : ",");
                        }else {
                            initData += "'" + initValue[i][j] + (j == initValue[i].length - 1 ? "');" : "',");
                        }
                    }
            initDataList.add(initData);
        }

        return initDataList;
    }

    public static String[] getColumnNames(){
        String[] columnNames = {COLUMN_DATE,COLUMN_INDATE,COLUMN_MODATE,COLUMN_DELETE};
        return columnNames;
    }

}
