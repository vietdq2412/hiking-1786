package com.example.myapplication.entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Hike implements Parcelable {
    private long id;
    private String name;
    private String location;
    private String date;
    private boolean parkingAvailability;
    private int lengthOfHike;
    private int peak;
    private int duration;
    private String description;
    private String difficultyLevel;

    public Hike() {
    }

    public Hike(String name) {
        this.name = name;
    }

    public Hike(long id, String name, String location, String date, boolean parkingAvailability, int peak, int duration, int lengthOfHike, String difficultyLevel, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.parkingAvailability = parkingAvailability;
        this.peak = peak;
        this.duration = duration;
        this.lengthOfHike = lengthOfHike;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
    }

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

    public boolean isParkingAvailability() {
        return parkingAvailability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeak() {
        return peak;
    }

    public void setPeak(int peak) {
        this.peak = peak;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
                ", peak=" + peak +
                ", duration=" + duration +
                ", description='" + description + '\'' +
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
        dest.writeString(description);
    }

    protected Hike(Parcel in) {
        id = in.readLong();
        name = in.readString();
        location = in.readString();
        date = in.readString();
        parkingAvailability = in.readByte() != 0;
        lengthOfHike = in.readInt();
        description = in.readString();
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
