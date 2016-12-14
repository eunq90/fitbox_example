package com.again.fitbox.fitbox_android.util;

import com.again.fitbox.fitbox_android.model.Aticle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jeong on 2016. 11. 13..
 */

public class SimpleDB {

    private static HashMap<String,Aticle> db = new HashMap<String,Aticle>();

    public static void addAticle(String index,Aticle aticle){
        db.put(index,aticle);
    }

    public static Aticle getAticle(String index){
        return db.get(index);
    }

    public static List<String> getIndexes(){

        Iterator<String> keys = db.keySet().iterator();

        List<String> keyList = new ArrayList<String>();

        while(keys.hasNext()){
           keyList.add(keys.next());
        }

        return keyList;

    }

}
