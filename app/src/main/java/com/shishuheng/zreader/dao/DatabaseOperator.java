package com.shishuheng.zreader.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.shishuheng.reader.datastructure.TxtDetail;
import com.shishuheng.zreader.domin.book.Book;
import com.shishuheng.zreader.domin.book.TextBook;

import java.io.File;
import java.util.List;

/**
 * Created by shishuheng on 2018/1/27.
 */

public class DatabaseOperator {
    //数据库名称
    public static String DATABASE_NAME = "BookReader.db";
    public static String TABLE_BOOKS = "Books";
    public static String TABLE_SETTINGS = "Settings";
    public static int DATABASE_VERSION = 1;

//    String q1 = "select readPointer from Books where path=?";
    BookInformationDatabaseOpenHelper helper;
    SQLiteDatabase db;
    Cursor cursor;
    public DatabaseOperator(Context context, String database, int version) {
        helper = new BookInformationDatabaseOpenHelper(context, database, null, version);
        db = helper.getWritableDatabase();
    }

    public int getInt(String table, String field, String field_PrimaryKey, String value_PrimaryKey) {
        int r = -1;
        String query = "select " +field+ " from " +table+ " where " +field_PrimaryKey+ "=?";
        cursor = db.rawQuery(query, new String[] {value_PrimaryKey});
        if (cursor.moveToFirst()) {
            r = cursor.getInt(cursor.getColumnIndex(field));
        }
        return r;
    }

    public String getString(String table, String field, String field_PrimaryKey, String value_PrimaryKey) {
        String r = null;
        String query = "select " +field+ " from " +table+ " where " +field_PrimaryKey+ "=?";
        cursor = db.rawQuery(query, new String[] {value_PrimaryKey});
        if (cursor.moveToFirst()) {
            r = cursor.getString(cursor.getColumnIndex(field));
        }
        return r;
    }

    public boolean insertData(String table, ContentValues values) {
        try {
            db.insert(table, null, values);
            return true;
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean insertFile(File txt) {
        try {
            ContentValues values = new ContentValues();
            values.put("bookFile", txt.getAbsolutePath());
            values.put("author", "");
            values.put("title", txt.getName());
            values.put("category", "");
            values.put("image", "");
            values.put("id", 0);
            values.put("readPointer", 0);
            values.put("codingFormat", 1);
            values.put("totality", 0);
            db.insert(TABLE_BOOKS, null, values);
            values.clear();
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean updateData(String table, ContentValues values, String field_PrimaryKey, String value_PrimaryKey) {
        try {
            db.update(table, values, field_PrimaryKey + "=?", new String[]{value_PrimaryKey});
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean deleteRecord(String table, String field_PrimaryKey, String value_PrimaryKey) {
        try {
            db.delete(table, field_PrimaryKey+"=?", new String[] {value_PrimaryKey});
            return true;
        } catch (SQLException sql) {
            Log.v("注意","SQLException");
            sql.printStackTrace();
            return false;
        }
    }

    public List getBookList() {
        List list = null;
        try {
            String query = "select * from Books";
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst() && list != null) {
                list.clear();
                for (int i = 0; i < cursor.getCount(); i++) {
//                    TxtDetail detail = new TxtDetail();
                    Book book = new Book();
                    book.setBookFile(new File(cursor.getString(cursor.getColumnIndex("bookFile"))));
                    book.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    book.setAuthorName(cursor.getString(cursor.getColumnIndex("authorName")));
                    book.setBookSize(cursor.getString(cursor.getColumnIndex("bookSize")));
                    book.setPage(cursor.getInt(cursor.getColumnIndex("page")));
                    book.setTotalPageNumber(cursor.getInt(cursor.getColumnIndex("totalPageNumber")));
                    list.add(book);
                    cursor.moveToNext();
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            return list;
        }
    }

    public void close() {
        try {
            if (helper != null)
                helper.close();
            if (cursor != null)
                cursor.close();
            if (db != null)
                db.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
