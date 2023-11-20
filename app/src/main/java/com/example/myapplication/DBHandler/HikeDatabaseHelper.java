package com.example.myapplication.DBHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HikeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hikes.db";
    private static final int DATABASE_VERSION = 4;

    // Hike Table
    public static final String TABLE_HIKES = "hikes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PARKING_AVAILABILITY = "parking_availability";
    public static final String COLUMN_LENGTH_OF_HIKE = "length_of_hike";
    public static final String COLUMN_DIFFICULTY_LEVEL = "difficulty_level";
    public static final String COLUMN_PEAK_OF_HIKE = "peak";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_DESCRIPTION = "description";


    private static final String DATABASE_CREATE_HIKE = "create table " + TABLE_HIKES + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_LOCATION + " text not null, "
            + COLUMN_DATE + " text not null, "
            + COLUMN_PARKING_AVAILABILITY + " integer not null, "
            + COLUMN_LENGTH_OF_HIKE + " integer not null, "
            + COLUMN_PEAK_OF_HIKE + " integer not null, "
            + COLUMN_DURATION + " integer not null, "
            + COLUMN_DIFFICULTY_LEVEL + " text not null, "
            + COLUMN_DESCRIPTION + " text);";

    // Observation Table
    public static final String TABLE_OBSERVATIONS = "observations";
    public static final String COLUMN_OBSERVATION_ID = "_id";
    public static final String COLUMN_OBSERVATION_NAME = "name";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_HIKE_ID = "hikeId";

    private static final String DATABASE_CREATE_OBSERVATIONS = "create table " + TABLE_OBSERVATIONS + "(" + COLUMN_OBSERVATION_ID + " integer primary key autoincrement, " + COLUMN_OBSERVATION_NAME + " text not null, " + COLUMN_COMMENT + " text, " + COLUMN_TIME + " text not null, " + COLUMN_HIKE_ID + " integer not null);";

    public HikeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_HIKE);
        database.execSQL(DATABASE_CREATE_OBSERVATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIKES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OBSERVATIONS);
        onCreate(db);
    }
}

