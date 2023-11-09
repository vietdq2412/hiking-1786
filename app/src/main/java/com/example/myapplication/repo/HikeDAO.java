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

    public final String TABLE_HIKES = "hikes";

    public final String COLUMN_ID = "_id";
    public final String COLUMN_NAME = "name";
    public final String COLUMN_LOCATION = "location";
    public final String COLUMN_DATE = "date";
    public final String COLUMN_PARKING_AVAILABILITY = "parking_availability";
    public final String COLUMN_LENGTH_OF_HIKE = "length_of_hike";
    public final String COLUMN_DIFFICULTY_LEVEL = "difficulty_level";
    public final String COLUMN_DESCRIPTION = "description";

    private HikeDAO(Context context) {
        dbHelper = new HikeDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
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
        values.put(COLUMN_NAME, hike.getName());
        values.put(COLUMN_LOCATION, hike.getLocation());
        values.put(COLUMN_DATE, hike.getDate());
        values.put(COLUMN_PARKING_AVAILABILITY, hike.isParkingAvailability() ? 1 : 0); // Convert boolean to integer
        values.put(COLUMN_LENGTH_OF_HIKE, hike.getLengthOfHike());
        values.put(COLUMN_DIFFICULTY_LEVEL, hike.getDifficultyLevel());
        values.put(COLUMN_DESCRIPTION, hike.getDescription());

        long insertId = database.insert(TABLE_HIKES, null, values);
        return insertId;
    }

    public void updateHike(Hike hike) {
        ContentValues values = new ContentValues();
        values.put(HikeDatabaseHelper.COLUMN_NAME, hike.getName());

        database.update(HikeDatabaseHelper.TABLE_HIKES, values, HikeDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(hike.getId())});
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
                hike.setParkingAvailability(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_PARKING_AVAILABILITY))));
                hike.setLengthOfHike(Integer.parseInt(cursor.getString(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_LENGTH_OF_HIKE))));
                hike.setDifficultyLevel(cursor.getString(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_DIFFICULTY_LEVEL)));
                hike.setDescription(cursor.getString(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_DESCRIPTION)));

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

