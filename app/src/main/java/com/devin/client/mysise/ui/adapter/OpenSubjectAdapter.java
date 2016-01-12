package com.devin.client.mysise.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.OpenSubjects;
import com.devin.client.mysise.ui.activity.student.DetailSubjectActivity;

/**
 * Created by 书凡 on 2015-11-12.
 */
public class OpenSubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private OpenSubjects openSubjects;

    public OpenSubjectAdapter(Context context, OpenSubjects openSubjects) {
        this.context = context;
        this.openSubjects = openSubjects;
    }

    public class OpenSubjectViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView mode;
        TextView score;
        public OpenSubjectViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
            mode = (TextView) itemView.findViewById(R.id.mode);
            score = (TextView) itemView.findViewById(R.id.score);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailSubjectActivity.class);
                    intent.putExtra("URL",openSubjects.getSubjects().get(getPosition()).getUrl());
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.opensubject_card_view,parent,false);
        return new OpenSubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OpenSubjectViewHolder){
            ((OpenSubjectViewHolder)holder).id.setText(openSubjects.getSubjects().get(position).getId());
            ((OpenSubjectViewHolder)holder).score.setText(openSubjects.getSubjects().get(position).getScore());
            ((OpenSubjectViewHolder)holder).mode.setText(openSubjects.getSubjects().get(position).getExammode());
            ((OpenSubjectViewHolder)holder).name.setText(openSubjects.getSubjects().get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return openSubjects.getSubjects().size();
    }
}
