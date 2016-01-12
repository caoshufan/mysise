package com.devin.client.mysise.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Attendance;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by 书凡 on 2015-11-11.
 */
public class AttendanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<Attendance> attendances;

    public AttendanceAdapter(Context context, List<Attendance> attendances) {
        this.context = context;
        this.attendances = attendances;
    }

    public class AttendancViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView statu;

        public AttendancViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            statu = (TextView)itemView.findViewById(R.id.statu);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.attendance_card_view,parent,false);
        return new AttendancViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AttendancViewHolder){
            ((AttendancViewHolder)holder).name.setText(attendances.get(position).getName());
            ((AttendancViewHolder)holder).statu.setText(attendances.get(position).getStatu());
        }
    }

    @Override
    public int getItemCount() {
        return attendances.size();
    }
}
