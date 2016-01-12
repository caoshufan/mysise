package com.devin.client.mysise.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Exams;

/**
 * Created by 书凡 on 2015-11-12.
 */
public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Exams exams = new Exams();

    public ExamAdapter(Context context, Exams exams) {
        this.context = context;
        this.exams = exams;
    }

    public class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView data;
        TextView room;
        TextView seat;
        TextView nothing;
        public ExamViewHolder(View itemView) {
            super(itemView);
            if (exams.getExams().size() == 0)
                nothing = (TextView)itemView.findViewById(R.id.nothing);
            else {
                name = (TextView) itemView.findViewById(R.id.name);
                data = (TextView) itemView.findViewById(R.id.date);
                room = (TextView) itemView.findViewById(R.id.room);
                seat = (TextView) itemView.findViewById(R.id.seat);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (exams.getExams().size() == 0)
            view = LayoutInflater.from(context).inflate(R.layout.nothing_card_view,parent,false);
        else view = LayoutInflater.from(context).inflate(R.layout.exam_card_view,parent,false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (exams.getExams().size() == 0){
            ((ExamViewHolder)holder).nothing.setText("nothing date");
        }else if (holder instanceof ExamViewHolder){
            ((ExamViewHolder)holder).name.setText(exams.getExams().get(position).getName());
            ((ExamViewHolder)holder).seat.setText(exams.getExams().get(position).getSeat());
            ((ExamViewHolder)holder).room.setText(exams.getExams().get(position).getExamroom());
            ((ExamViewHolder)holder).data.setText(exams.getExams().get(position).getDate());
        }
    }

    @Override
    public int getItemCount() {
        return exams.getExams().size();
    }
}
