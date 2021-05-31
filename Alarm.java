package com.example.alarm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alarms")
public class Alarm {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "time")
    public String content;
}
