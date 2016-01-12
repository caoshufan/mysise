package com.devin.client.mysise.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Status;


/**
 * Created by 书凡 on 2015-11-12.
 */
public class StatuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Status status;

    public StatuAdapter(Context context, Status status) {
        this.context = context;
        this.status = status;
    }

    public class StatuViewHolder extends RecyclerView.ViewHolder {

        TextView reason;
        TextView time;
        TextView date;
        TextView nothing;
        public StatuViewHolder(View itemView) {
            super(itemView);
            if (status.getStatus().size() == 0){
                nothing = (TextView) itemView.findViewById(R.id.nothing);
            }else {
                reason = (TextView) itemView.findViewById(R.id.reason);
                time = (TextView)itemView.findViewById(R.id.time);
                date = (TextView) itemView.findViewById(R.id.date);
            }

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (status.getStatus().size() == 0)
            view = LayoutInflater.from(context).inflate(R.layout.nothing_card_view,parent,false);
        else view = LayoutInflater.from(context).inflate(R.layout.statu_card_view,parent,false);
        return new StatuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (status.getStatus().size() == 0){
            ((StatuViewHolder)holder).nothing.setText("nothing date");
        }else if (holder instanceof StatuViewHolder){
            ((StatuViewHolder)holder).reason.setText(status.getStatus().get(position).getReason());
            ((StatuViewHolder)holder).date.setText(status.getStatus().get(position).getDay());
            ((StatuViewHolder)holder).time.setText(status.getStatus().get(position).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return status.getStatus().size();
    }
}
