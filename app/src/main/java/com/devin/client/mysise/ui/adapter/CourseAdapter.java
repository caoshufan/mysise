package com.devin.client.mysise.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Course;

import java.util.List;

/**
 * Created by 书凡 on 2015-12-23.
 */
public class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;

    private List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView name;
        TextView score;
        TextView check;

        public CourseViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
            score = (TextView) itemView.findViewById(R.id.score);
            check = (TextView) itemView.findViewById(R.id.check);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CourseViewHolder){
            ((CourseViewHolder)holder).id.setText(courses.get(position).getClassNO());
            ((CourseViewHolder)holder).name.setText(courses.get(position).getClassName());
            ((CourseViewHolder)holder).score.setText(courses.get(position).getCreadit());
            ((CourseViewHolder)holder).check.setText(courses.get(position).getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
