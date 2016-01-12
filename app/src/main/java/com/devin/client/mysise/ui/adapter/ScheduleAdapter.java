package com.devin.client.mysise.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 书凡 on 2015-11-12.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private Schedule schedule;

    private List<String> scheduleofone = new ArrayList<>();

    private int week;

    public ScheduleAdapter(Context context, Schedule schedule, int week) {
        this.context = context;
        this.schedule = schedule;
        this.week = week;
        selectWeek();
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder{
        TextView time;
        TextView name;
        public ScheduleViewHolder(View itemView) {
            super(itemView);
            time = (TextView)itemView.findViewById(R.id.time);
            name = (TextView)itemView.findViewById(R.id.name);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_card_view,parent,false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ScheduleViewHolder){
            ((ScheduleViewHolder)holder).time.setText(schedule.getTime().get(position));
            ((ScheduleViewHolder)holder).name.setText(scheduleofone.get(position)); //TODO 如果空的要注意变成无课
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    private void selectWeek(){
        switch (week){
            case 1:
                change(schedule.getOne());
                break;
            case 2:
                change(schedule.getTwo());
                break;
            case 3:
                change(schedule.getThree());
                break;
            case 4:
                change(schedule.getFour());
                break;
            case 5:
                change(schedule.getFive());
                break;
        }
    }

    private void change(List<String> ss){
        scheduleofone.clear();
        for (String str : ss){
            if (str.equals(" ")){
                scheduleofone.add("无课");
            }else {
                scheduleofone.add(str);
            }
        }
    }

}
