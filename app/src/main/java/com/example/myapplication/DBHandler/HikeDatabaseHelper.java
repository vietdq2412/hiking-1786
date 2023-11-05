package com.example.myapplication.DBHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HikeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hikes.db";
    private static final int DATABASE_VERSION = 1;

    // Table Name
    public static final String TABLE_HIKES = "hikes";

    // Table Columns
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DATE = "date";
    // ... Add other columns like parking availability, length, etc.

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table " + TABLE_HIKES
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_LOCATION + " text not null, "
            + COLUMN_DATE + " text not null);";

    public HikeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIKES);
        onCreate(db);
    }
}

