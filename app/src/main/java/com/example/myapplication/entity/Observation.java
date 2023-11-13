package com.example.myapplication.entity;

import android.widget.EditText;

import java.util.Date;

public class Observation {
    private long id;
    private String name;
    private String comment;
    private Date time;
    private long hikeId;

    public Observation(String name, String comment, Date time, long hikeId) {
        this.name = name;
        this.comment = comment;
        this.time = time;
        this.hikeId = hikeId;
    }

    public Observation(long id, String name, String comment, Date time, long hikeId) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.time = time;
        this.hikeId = hikeId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public long getHikeId() {
        return hikeId;
    }

    public void setHikeId(long hikeId) {
        this.hikeId = hikeId;
    }
}
