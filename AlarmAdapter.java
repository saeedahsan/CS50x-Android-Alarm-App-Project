package com.example.alarm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    public static class AlarmViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView nameTextView;

        public AlarmViewHolder(View view) {
            super(view);
            this.containerView = view.findViewById(R.id.alarm_row);
            this.nameTextView = view.findViewById(R.id.alarm_row_name);
            this.containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Alarm alarm = (Alarm) containerView.getTag();
                    Intent intent = new Intent(v.getContext(), AlarmActivity.class);
                    intent.putExtra("id", alarm.id);
                    intent.putExtra("content", alarm.content);

                    context.startActivity(intent);
                }
            });

        }
    }

    private List<Alarm> alarms = new ArrayList<>();

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_row, parent, false);

        return new AlarmViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        Alarm current = alarms.get(position);
        holder.containerView.setTag(current);
        holder.nameTextView.setText(current.content);
    }
    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public void reload() {
        alarms = MainActivity.database.alarmDao().getAll();
        notifyDataSetChanged();
    }
}
