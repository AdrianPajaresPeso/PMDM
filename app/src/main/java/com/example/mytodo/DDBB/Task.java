package com.example.mytodo.DDBB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "task")
    public String mTask;
    @ColumnInfo(name = "desc")
    public String mDesc;
    @ColumnInfo(name = "finished")
    public boolean mFinished;

    public Task(String mTask, String mDesc, boolean mFinished) {
        this.mTask = mTask;
        this.mDesc = mDesc;
        this.mFinished = mFinished;
    }


}