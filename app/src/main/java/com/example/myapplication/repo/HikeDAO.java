package com.example.myapplication.repo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.example.myapplication.DBHandler.HikeDatabaseHelper;
import com.example.myapplication.entity.Hike;

import java.util.ArrayList;
import java.util.List;

public class HikeDAO {
    private static HikeDAO instance;
    private SQLiteDatabase database;
    private HikeDatabaseHelper dbHelper;
    public List<Hike> hikes = new ArrayList<>();


    private HikeDAO(Context context) {
        dbHelper = new HikeDatabaseHelper(context);
    }

    // Public method to provide access to the singleton instance
    public static synchronized HikeDAO getInstance(Context context) {
        if (instance == null) {
            instance = new HikeDAO(context.getApplicationContext()); // Use ApplicationContext to avoid memory leaks
        }
        return instance;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addHike(Hike hike) {
        ContentValues values = new ContentValues();
        values.put(HikeDatabaseHelper.COLUMN_NAME, hike.getName());
        values.put(HikeDatabaseHelper.COLUMN_LOCATION, hike.getLocation());
        values.put(HikeDatabaseHelper.COLUMN_DATE, hike.getDate());


        return database.insert(HikeDatabaseHelper.TABLE_HIKES, null, values);
    }

    public void updateHike(Hike hike) {
        ContentValues values = new ContentValues();
        values.put(HikeDatabaseHelper.COLUMN_NAME, hike.getName());

        database.update(HikeDatabaseHelper.TABLE_HIKES, values,
                HikeDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(hike.getId())});
    }

    public void deleteHike(long hikeId) {
        database.delete(HikeDatabaseHelper.TABLE_HIKES, HikeDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(hikeId)});
    }

    @SuppressLint("Range")
    public List<Hike> getAllHikes() {
        List<Hike> hikes = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + HikeDatabaseHelper.TABLE_HIKES;
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Hike hike = new Hike();
                hike.setId(cursor.getLong(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_ID)));
                hike.setName(cursor.getString(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_NAME)));
                hike.setLocation(cursor.getString(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_LOCATION)));
                hike.setDate(cursor.getString(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_DATE)));
                // ... retrieve other fields ...

                hikes.add(hike);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return hikes;
    }

    public void clearAllHikes() {
        database.delete(HikeDatabaseHelper.TABLE_HIKES, null, null);
    }
}

