package com.devin.client.mysise.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Subject;

import java.util.List;

/**
 * Created by 书凡 on 2015-11-11.
 */
public class SubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Subject> subjects;
    private Context context;

    public SubjectAdapter(List<Subject> subjects, Context context) {
        this.subjects = subjects;
        this.context = context;
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView name;
        TextView score;
        TextView term;
        public SubjectViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
            score = (TextView) itemView.findViewById(R.id.score);
            term = (TextView) itemView.findViewById(R.id.term);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View subject = LayoutInflater.from(context).inflate(R.layout.list_item_user_subject,parent,false);
        return new SubjectViewHolder(subject);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SubjectViewHolder){
            ((SubjectViewHolder)holder).id.setText(subjects.get(position).getId());
            ((SubjectViewHolder)holder).name.setText(subjects.get(position).getName());
            ((SubjectViewHolder)holder).term.setText(subjects.get(position).getTerm());
            ((SubjectViewHolder)holder).score.setText(subjects.get(position).getScore());
        }
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

}
