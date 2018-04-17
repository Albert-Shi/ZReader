package com.shishuheng.zreader.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shishuheng on 2018/1/9.
 */

public class BookInformationDatabaseOpenHelper extends SQLiteOpenHelper {
//    private static String CREAT_BOOKSINFO = "create table Books("
//            + "path text primary key,"
//            + "author text,"
//            + "title text,"
//            + "category text,"
//            + "image text,"
//            + "id integer,"
//            + "readPointer integer,"
//            + "codingFormat integer,"
//            + "totality integer)";
//
//    private static String CREAT_SETTING = "create table Settings(" +
//            "id integer primary key," +
//            "textSize integer)";
    List<String> sqls = null;

    private Context mContext;

    private Object[] objects;

    public BookInformationDatabaseOpenHelper(Context context, Object[] objects, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
        this.objects = objects;
        sqls = Arrays.asList(ObjectToTable.getCreateSQLs(objects));
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREAT_SETTING);
//        db.execSQL(CREAT_BOOKSINFO);
        for (String sql : sqls) {
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
