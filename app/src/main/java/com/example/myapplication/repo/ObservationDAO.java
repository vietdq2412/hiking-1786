package com.example.myapplication.repo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DBHandler.HikeDatabaseHelper;
import com.example.myapplication.entity.Observation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObservationDAO {
    private static ObservationDAO instance;
    private HikeDatabaseHelper dbHelper;

    private ObservationDAO(Context context) {
        dbHelper = new HikeDatabaseHelper(context);
    }

    public static synchronized ObservationDAO getInstance(Context context) {
        if (instance == null) {
            instance = new ObservationDAO(context.getApplicationContext());
        }
        return instance;
    }

    public long insertObservation(Observation observation) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HikeDatabaseHelper.COLUMN_OBSERVATION_NAME, observation.getName());
        values.put(HikeDatabaseHelper.COLUMN_COMMENT, observation.getComment());
        values.put(HikeDatabaseHelper.COLUMN_TIME, observation.getTime().toString());
        values.put(HikeDatabaseHelper.COLUMN_HIKE_ID, observation.getHikeId());

        long id = db.insert(HikeDatabaseHelper.TABLE_OBSERVATIONS, null, values);
        db.close();
        return id;
    }

    public List<Observation> getAllObservations() {
        List<Observation> observations = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {
                HikeDatabaseHelper.COLUMN_OBSERVATION_ID,
                HikeDatabaseHelper.COLUMN_OBSERVATION_NAME,
                HikeDatabaseHelper.COLUMN_COMMENT,
                HikeDatabaseHelper.COLUMN_TIME,
                HikeDatabaseHelper.COLUMN_HIKE_ID
        };
        Cursor cursor = db.query(HikeDatabaseHelper.TABLE_OBSERVATIONS, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Observation observation = new Observation(
                        cursor.getLong(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_OBSERVATION_ID)),
                        cursor.getString(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_OBSERVATION_NAME)),
                        cursor.getString(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_COMMENT)),
                        new Date(cursor.getString(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_TIME))),
                        cursor.getLong(cursor.getColumnIndex(HikeDatabaseHelper.COLUMN_HIKE_ID))
                );
                observations.add(observation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return observations;
    }

    public List<Observation> getAllObservationsByHikeID(long hikeId) {
        List<Observation> observations = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a selection criteria
        String selection = HikeDatabaseHelper.COLUMN_HIKE_ID + " = ?";
        String[] selectionArgs = { String.valueOf(hikeId) };

        // Columns to retrieve
        String[] columns = {
                HikeDatabaseHelper.COLUMN_OBSERVATION_ID,
                HikeDatabaseHelper.COLUMN_OBSERVATION_NAME,
                HikeDatabaseHelper.COLUMN_COMMENT,
                HikeDatabaseHelper.COLUMN_TIME,
                HikeDatabaseHelper.COLUMN_HIKE_ID
        };

        // Query the database
        Cursor cursor = db.query(
                HikeDatabaseHelper.TABLE_OBSERVATIONS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Iterate over the cursor and populate the list of observations
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(HikeDatabaseHelper.COLUMN_OBSERVATION_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(HikeDatabaseHelper.COLUMN_OBSERVATION_NAME));
                String comment = cursor.getString(cursor.getColumnIndexOrThrow(HikeDatabaseHelper.COLUMN_COMMENT));
                String time = cursor.getString(cursor.getColumnIndexOrThrow(HikeDatabaseHelper.COLUMN_TIME));
                long hikeID = cursor.getLong(cursor.getColumnIndexOrThrow(HikeDatabaseHelper.COLUMN_HIKE_ID));

                Observation observation = new Observation(id, name, comment, new Date(time), hikeID);
                observations.add(observation);
            } while (cursor.moveToNext());
        }

        // Close the cursor to avoid memory leaks
        cursor.close();
        // Close the database connection
        db.close();

        return observations;
    }
    public void deleteObservation(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(HikeDatabaseHelper.TABLE_OBSERVATIONS, HikeDatabaseHelper.COLUMN_OBSERVATION_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
