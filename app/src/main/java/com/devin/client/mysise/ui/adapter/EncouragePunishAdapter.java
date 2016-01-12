package com.devin.client.mysise.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Encourages;

/**
 * Created by 书凡 on 2015-11-11.
 */
public class EncouragePunishAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private Encourages encourages;

    public class EncouragePunishViewHolder extends RecyclerView.ViewHolder {
        TextView department;
        TextView reason;
        TextView date;
        TextView nothing;

        public EncouragePunishViewHolder(View itemView) {
            super(itemView);
            if (encourages.getEncourages().size() == 0) {
                nothing = (TextView) itemView.findViewById(R.id.nothing);
            } else {
                department = (TextView) itemView.findViewById(R.id.department);
                reason = (TextView) itemView.findViewById(R.id.reason);
                date = (TextView) itemView.findViewById(R.id.date);
            }
        }
    }

    public EncouragePunishAdapter(Context context, Encourages encourages) {
        this.context = context;
        this.encourages = encourages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (encourages.getEncourages().size() == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.nothing_card_view, parent, false);
        } else
            view = LayoutInflater.from(context).inflate(R.layout.encourage_punish_card_view, parent, false);
        return new EncouragePunishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (encourages.getEncourages().size() == 0) {
            ((EncouragePunishViewHolder) holder).nothing.setText("nothing date");
        } else if(holder instanceof EncouragePunishViewHolder) {
            ((EncouragePunishViewHolder) holder).reason.setText(encourages.getEncourages().get(position).getReason());
            ((EncouragePunishViewHolder) holder).department.setText(encourages.getEncourages().get(position).getDepartment());
            ((EncouragePunishViewHolder) holder).date.setText(encourages.getEncourages().get(position).getDate());
        }
    }

    @Override
    public int getItemCount() {
        return encourages.getEncourages().size();
    }
}
