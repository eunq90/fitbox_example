package com.again.fitbox.fitbox_android.database;


import com.again.fitbox.fitbox_android.database.table.BoardTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeong on 2016. 11. 27..
 */

public class FBDatabaseCreator implements DatabaseCreator {


    @Override
    public String[] getCreateTablesStmt() {
        String[] tableStmt = {
                BoardTable.TABLE_CREATE_DATATABLE
        };

        return tableStmt;
    }

    @Override
    public String[] getCreateIndexStmt() {
        String[] indexStmt = {
                BoardTable.INDEX_CREATE_DATATABLE
        };
        return indexStmt;
    }

    @Override
    public String[] getCreateViewStmt() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getCreateTriggerStmt() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<List<String>> getInitDataInsertStmt() {

        ArrayList<List<String>> initDataList = new ArrayList<List<String>>();

        initDataList.add(BoardTable.InitDataInsert());


        return initDataList;
    }


}
