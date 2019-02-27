package com.example.admin215.mylibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin215 on 20.02.2019.
 */

public class OpenHelper extends SQLiteOpenHelper {

    public static final String TABLENAME = "books";
    public static final String ID_COLUMN = "_id";
    public static final String AUTHOR_COLUMN = "author";
    public static final String TITLE_COLUMN = "title";
    public static final String YEAR_COLUMN = "year";
    public static final String GENRE_COLUMN = "genre";

    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStr = "CREATE TABLE " + TABLENAME + "(" +
                ID_COLUMN + " integer primary key autoincrement, " +
                AUTHOR_COLUMN + " text, " + TITLE_COLUMN + " text NOT NULL, " +
                YEAR_COLUMN + " integer, " + GENRE_COLUMN + " text);";
        db.execSQL(sqlStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(db);
    }
}
