package com.zjh.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ZhangJinghao on 2018/8/15.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDBHelper";

    private static final String CREATE_PLAYER = "" +
//            "create table player(\n" +
//            "id integer primary key autoincrement,\n" +
//            "name text,\n" +
//            "age integer,\n" +
//            "isMale blob);";
            "CREATE TABLE [player](\n" +
            "  [id] integer PRIMARY KEY AUTOINCREMENT, \n" +
            "  [name] text, \n" +
            "  [age] integer, \n" +
            "  [isMale] blob);";

//    private static final String CREATE_CATEGORY = "" +
//            "create table category(\n" +
//            "id integer primary key autoincrement,\n" +
//            "category_name text,\n" +
//            "category_code integer);";

    public MyDBHelper(Context context) {
        super(context, "game.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "@zjh Database onCreate");
        sqLiteDatabase.execSQL(CREATE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "@zjh Database onUpgrade: " + i1);
//        sqLiteDatabase.execSQL(CREATE_CATEGORY);
    }
}
