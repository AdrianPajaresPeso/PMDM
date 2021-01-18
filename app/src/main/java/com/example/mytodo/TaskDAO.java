package com.example.mytodo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {

    @Query("SELECT * FROM TASK")
    public List<Task> getAll();

    @Insert
    public void insert(Task task);

    @Query("DELETE FROM TASK")
    public void deleteAll();
}
