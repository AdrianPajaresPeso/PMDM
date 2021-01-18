package com.example.mytodo.DDBB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Task")
    private String mTask;

    @ColumnInfo(name = "Description")
    private String mDesc;

    @ColumnInfo(name = "Finished")
    private Boolean mFinished;

    public Task(String task, String desc, boolean finished) {
        this.mTask = task;
        this.mDesc = desc;
        this.mFinished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmTask() {
        return mTask;
    }

    public void setmTask(String mTask) {
        this.mTask = mTask;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public Boolean getmFinished() {
        return mFinished;
    }

    public void setmFinished(Boolean mFinished) {
        this.mFinished = mFinished;
    }
}
