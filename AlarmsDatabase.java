package com.example.alarm;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Alarm.class}, version = 1)
public abstract class AlarmsDatabase extends RoomDatabase {
    public abstract AlarmDao alarmDao();
}