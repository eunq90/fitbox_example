package com.again.fitbox.fitbox_android.database;

import java.util.List;

/**
 * Created by jeong on 2016. 11. 27..
 */

public interface DatabaseCreator {

    public static final String DB_NAME = "fitbox_db";
    public static final int DB_VERSION = 2;

    public String[] getCreateTablesStmt();
    public String[] getCreateIndexStmt();
    public String[] getCreateViewStmt();
    public String[] getCreateTriggerStmt();
    public List<List<String>> getInitDataInsertStmt();
}
