package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AlarmAdapter adapter;
    public static AlarmsDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Room
                .databaseBuilder(getApplicationContext(), AlarmsDatabase.class, "notes")
                .allowMainThreadQueries()
                .build();
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AlarmAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.add_note_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.alarmDao().create();
                adapter.reload();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        adapter.reload();
    }
}