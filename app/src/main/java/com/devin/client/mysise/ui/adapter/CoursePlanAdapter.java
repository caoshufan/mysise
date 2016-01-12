package com.devin.client.mysise.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.CoursePlan;

import java.util.ArrayList;

/**
 * Created by 书凡 on 2015-12-24.
 */
public class CoursePlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    ArrayList<CoursePlan> coursePlans;

    public CoursePlanAdapter(Context context, ArrayList<CoursePlan> coursePlans) {
        this.context = context;
        this.coursePlans = coursePlans;
    }

    public class CoursePlanViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView score;
        TextView name;
        TextView check;
        public CoursePlanViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            score = (TextView) itemView.findViewById(R.id.score);
            name = (TextView) itemView.findViewById(R.id.name);
            check = (TextView) itemView.findViewById(R.id.check);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_course,parent,false);
        return new CoursePlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CoursePlanViewHolder){
            ((CoursePlanViewHolder)holder).id.setText(coursePlans.get(position).getId());
            ((CoursePlanViewHolder)holder).name.setText(coursePlans.get(position).getName());
            ((CoursePlanViewHolder)holder).score.setText(coursePlans.get(position).getScore());
            ((CoursePlanViewHolder)holder).check.setText(coursePlans.get(position).getCheck());
        }
    }

    @Override
    public int getItemCount() {
        return coursePlans.size();
    }
}
