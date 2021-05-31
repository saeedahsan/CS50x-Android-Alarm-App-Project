package com.example.alarm;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlarmDao {
    @Query("INSERT INTO alarms (time) VALUES ('23:59')")
    void create();

    @Query("SELECT * FROM alarms")
    List<Alarm> getAll();

    @Query("UPDATE alarms SET time = :content WHERE id = :id")
    void save(String content, int id);

    @Query("DELETE FROM alarms WHERE id = :id")
    void delete(int id);
}
