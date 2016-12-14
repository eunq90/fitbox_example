package com.again.fitbox.fitbox_android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by jeong on 2016. 11. 26..
 */

public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String CLASSNAME = DatabaseHelper.class.getSimpleName();
        private static final String KEY_COLUMN = "_id";

        private static DatabaseHelper mInstance;
        private static SQLiteDatabase db;

        /***
         * 생성자
         *
         * @param context   : app context
         * @param name      : database name
         * @param factory   : cursor Factory
         * @param version   : DB version
         */
        private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            Log.v(Constants.LOG_TAG,  DatabaseHelper.CLASSNAME + "Create or Open database : "+name);
        }

        /***
         * 생성자
         *
         * @param context   : app context
         */
        private DatabaseHelper(final Context context) {
            super(context, DatabaseCreator.DB_NAME , null, DatabaseCreator.DB_VERSION);
            Log.v(Constants.LOG_TAG,  DatabaseHelper.CLASSNAME + "Create or Open database : "+ DatabaseCreator.DB_NAME);
        }

        /***
         * Initialize method
         *
         * @param context       : application context
         */
        private static void initialize(Context context) {
            if(mInstance == null) {

                Log.i(Constants.LOG_TAG, DatabaseHelper.CLASSNAME + "Try to create instance of database (" + DatabaseCreator.DB_NAME + ")");
                mInstance = new DatabaseHelper(context);

                try {
                    Log.i(Constants.LOG_TAG, " Creating or opening the database ( " + DatabaseCreator.DB_NAME + " ).");
                    db = mInstance.getWritableDatabase();
                } catch (SQLiteException se) {
                    Log.e(Constants.LOG_TAG, " Cound not create and/or open the database ( " + DatabaseCreator.DB_NAME + " ) that will be used for reading and writing.", se);
                }
                Log.i(Constants.LOG_TAG,  DatabaseHelper.CLASSNAME + " instance of database (" + DatabaseCreator.DB_NAME + ") created !");
            }
        }

        /***
         * Static method for getting singleton instance
         *
         * @param context       : application context
         * @return              : singleton instance
         */
        public static final DatabaseHelper getInstance(Context context) {
            initialize(context);
            return mInstance;
        }

        /***
         * Method to close database & instance null
         */
        public void close() {
            if(mInstance != null) {
                Log.i(Constants.LOG_TAG, DatabaseHelper.CLASSNAME + "Closing the database [ " + DatabaseCreator.DB_NAME + " ].");
                db.close();
                mInstance = null;
            }
        }

        /***
         * Method for select table
         * db.query wrapper
         * @param table     : table name
         * @param columns   : column name array
         * @return          : cursor
         */
        public Cursor get(String table, String[] columns){
            return db.query(table, columns, null, null, null, null, null);
        }

        /***
         * Method for select table
         * @param table     : table name
         * @param columns   : column name array
         * @param id        : record id (pk 컬러명은 "_id" 만 가능함)
         * @return          : cursor
         */
        public Cursor get(String table, String[] columns, long id){
            Cursor cursor = db.query(true, table, columns, KEY_COLUMN + "=" + id, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor;
        }

        /****
         * Method for select statements
         * @param sql       : sql statements
         * @return          : cursor
         */
        public Cursor get(String sql) {
            return db.rawQuery(sql, null);
        }

        /***
         * Method to insert record
         * @param table     : table name
         * @param values    : ContentValues instance
         * @return          : long (rowid)
         */
        public long insert(String table, ContentValues values) {
            return db.insert(table, null, values);
        }

        /***
         * Method to update record
         * @param table     : table name
         * @param values    : ContentValues instance
         * @param id        : record id
         * @return          : int
         */
        public int update(String table, ContentValues values, long id) {
            return db.update(table, values, KEY_COLUMN + "=" + id, null);
        }

        /***
         * Method to update record
         * @param table         : table name
         * @param values        : ContentValues instance
         * @param whereClause   : Where Clause
         * @return              ; int
         */
        public int update(String table, ContentValues values, String whereClause) {
            return db.update(table, values, whereClause, null);
        }

        /***
         * Method to delete record
         * @param table         : table name
         * @param whereClause   : Where Clause
         * @return              : int
         */
        public int delete(String table, String whereClause) {
            return db.delete(table, whereClause, null);
        }

        /***
         * Method to delete record
         * @param table         : table name
         * @param id            : record id
         * @return              : int
         */
        public int delete(String table, long id) {
            return db.delete(table, KEY_COLUMN + "=" + id, null);
        }

        /***
         * Method to run sql
         * @param sql
         */
        public void exec(String sql) {
            db.execSQL(sql);
        }

        /****
         * logCursorInfo    : Cursor로 리턴받는 Result를 로깅하는 메소드
         * @param c
         */
        public void logCursorInfo(Cursor c) {
            Log.i(Constants.LOG_TAG, "*** Cursor Begin *** " + "Results:" +
                    c.getCount() + " Colmns: " + c.getColumnCount());

            // Column Name print
            String rowHeaders = "|| ";
            for(int i=0; i<c.getColumnCount(); i++) {
                rowHeaders = rowHeaders.concat(c.getColumnName(i) + " || ");
            }

            Log.i(Constants.LOG_TAG, "COLUMNS " + rowHeaders);
            // Record Print
            c.moveToFirst();
            while(c.isAfterLast() == false) {
                String rowResults = "|| ";
                for(int i=0; i < c.getColumnCount(); i++) {
                    rowResults = rowResults.concat(c.getString(i) + " || ");
                }

                Log.i(Constants.LOG_TAG, "Row " + c.getPosition() + ": " + rowResults);

                c.moveToNext();
            }
            Log.i(Constants.LOG_TAG, "*** Cursor End ***");
        }

        @Override
        /***
         * Method to create database
         * 데이터베이스 생성. 최초 한번만 실행됨.
         * @param db        :SQLiteDatabase instance
         */
        public void onCreate(SQLiteDatabase db) {
            DatabaseCreator mCreator = new FBDatabaseCreator();
            String[] tableCreateStmt = mCreator.getCreateTablesStmt();
            String[] indexCreateStmt = mCreator.getCreateIndexStmt();
            List<List<String>> initDataDml = mCreator.getInitDataInsertStmt();

            try {
                if(tableCreateStmt != null && tableCreateStmt.length > 0) {
                    Log.v(Constants.LOG_TAG, DatabaseHelper.CLASSNAME + " - onCreate() : Table Creation");
                    for(int i = 0; i < tableCreateStmt.length; i++) {
                        db.execSQL(tableCreateStmt[i]);
                    }
                }

                if(indexCreateStmt != null && indexCreateStmt.length > 0) {
                    Log.v(Constants.LOG_TAG, DatabaseHelper.CLASSNAME + " - onCreate() : Index Creation");
                    for(int i = 0; i < indexCreateStmt.length; i++) {
                        db.execSQL(indexCreateStmt[i]);
                    }
                }

                if(initDataDml != null && initDataDml.size() > 0) {
                    for(List<String> insertList : initDataDml ) {
                        for(String insertQuery : insertList ){
                            Log.v(Constants.LOG_TAG, DatabaseHelper.CLASSNAME + " - onCreate() : Data Load" + insertQuery);
                            db.execSQL(insertQuery);

                        }
                    }
                    Log.v(Constants.LOG_TAG, DatabaseHelper.CLASSNAME + " - onCreate() : Init Data Load");
                }

            } catch(SQLException e) {
                Log.e(Constants.LOG_TAG, DatabaseHelper.CLASSNAME + " - onCreate() : Table Creation Error", e);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.v(Constants.LOG_TAG, DatabaseHelper.CLASSNAME + " - onUpgrade() : Table Upgrade Action");

        }

    }
