package com.example.myapplication.entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Hike implements Parcelable {
    private long id;
    private String name;
    private String location;
    private String date;
    private boolean parkingAvailability;  // I'm using String, but this could be a boolean if you just want to specify yes/no
    private int lengthOfHike;         // Assuming this is a string like "5 miles" but could be a float or double
    private String difficultyLevel;      // "Easy", "Moderate", "Hard"

    // Default constructor
    public Hike() {
    }

    public Hike(String name) {
        this.name = name;
    }

    // Parameterized constructor
    public Hike(long id, String name, String location, String date, boolean parkingAvailability, int lengthOfHike, String difficultyLevel) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.parkingAvailability = parkingAvailability;
        this.lengthOfHike = lengthOfHike;
        this.difficultyLevel = difficultyLevel;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getParkingAvailability() {
        return parkingAvailability;
    }

    public void setParkingAvailability(boolean parkingAvailability) {
        this.parkingAvailability = parkingAvailability;
    }

    public int getLengthOfHike() {
        return lengthOfHike;
    }

    public void setLengthOfHike(int lengthOfHike) {
        this.lengthOfHike = lengthOfHike;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public String toString() {
        return "Hike{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", parkingAvailability=" + parkingAvailability +
                ", lengthOfHike=" + lengthOfHike +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(date);
        dest.writeByte((byte) (parkingAvailability ? 1 : 0));
        dest.writeInt(lengthOfHike);
        dest.writeString(difficultyLevel);
    }

    protected Hike(Parcel in) {
        id = in.readLong();
        name = in.readString();
        location = in.readString();
        date = in.readString();
        parkingAvailability = in.readByte() != 0;
        lengthOfHike = in.readInt();
        difficultyLevel = in.readString();
    }
    public static final Creator<Hike> CREATOR = new Creator<Hike>() {
        @Override
        public Hike createFromParcel(Parcel in) {
            return new Hike(in);
        }

        @Override
        public Hike[] newArray(int size) {
            return new Hike[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
