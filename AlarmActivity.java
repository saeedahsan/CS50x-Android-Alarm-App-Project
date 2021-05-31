package com.example.alarm;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {
    private EditText hourText;
    private EditText minuteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        hourText = findViewById(R.id.editTextHour);
        minuteText = findViewById(R.id.editTextMinute);
        Intent intent = getIntent();
        hourText.setText(intent.getStringExtra("content").substring(0,2));
        minuteText.setText(intent.getStringExtra("content").substring(3,5));
        Button button = findViewById(R.id.button);
        Button deleteButton = findViewById(R.id.delete_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = Integer.parseInt(hourText.getText().toString());
                int minute = Integer.parseInt(minuteText.getText().toString());

                Intent intent1 = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent1.putExtra(AlarmClock.EXTRA_HOUR,hour);
                intent1.putExtra(AlarmClock.EXTRA_MINUTES,minute);

                if (hour < 24 && minute < 60) {
                    Intent intent = getIntent();
                    int id = intent.getIntExtra("id", 0);
                    MainActivity.database.alarmDao().save((hourText.getText().toString()) + ":" + minuteText.getText().toString(), id);
                    startActivity(intent1);
                    onBackPressed();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Time", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v) {
                int hour = Integer.parseInt(hourText.getText().toString());
                int minute = Integer.parseInt(minuteText.getText().toString());

                Intent intent1 = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
                intent1.putExtra(AlarmClock.EXTRA_ALARM_SEARCH_MODE,AlarmClock.ALARM_SEARCH_MODE_TIME);
                intent1.putExtra(AlarmClock.EXTRA_HOUR,hour);
                intent1.putExtra(AlarmClock.EXTRA_MINUTES,minute);
                Toast toast = Toast.makeText(getApplicationContext(), "Select Alarm To Confirm Delete", Toast.LENGTH_SHORT);
                toast.show();
                startActivity(intent1);

                Intent intent = getIntent();
                int id = intent.getIntExtra("id", 0);
                MainActivity.database.alarmDao().delete(id);
                onBackPressed();
            }
        });
    }
}
