package com.devin.client.mysise.ui.activity.student;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Status;
import com.devin.client.mysise.model.parse.ParseStatu;
import com.devin.client.mysise.ui.activity.SwipeBackActivity;
import com.devin.client.mysise.ui.adapter.StatuAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StatusActivity extends SwipeBackActivity {

    private static final int UPDATE_STATUS = 1;

    public static Status status = new Status();

    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_STATUS:
                    setStatus();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_status;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init(){

        setTitle("晚归违规表");

        recycler.setLayoutManager(new LinearLayoutManager(this));

        swipe.setRefreshing(true);

        requestStatus();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestStatus();
            }
        });
    }

    private void requestStatus(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                status.getStatus().clear();
                status = ParseStatu.getStatus();
                Message message = new Message();
                message.what = UPDATE_STATUS;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void setStatus(){
        recycler.setAdapter(new StatuAdapter(this,status));
        swipe.setRefreshing(false);
    }
}
