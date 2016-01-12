package com.devin.client.mysise.ui.activity.student;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.OpenSubjects;
import com.devin.client.mysise.model.parse.ParseOpenSubject;
import com.devin.client.mysise.ui.activity.SwipeBackActivity;
import com.devin.client.mysise.ui.adapter.OpenSubjectAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OpenSubjectActivity extends SwipeBackActivity {

    private static final int OPEN = 1;

    public static OpenSubjects openSubjects = new OpenSubjects();
    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case OPEN:
                    setOpenSubjects();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_open_subject;
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

        setTitle("开设课程");

        recycler.setLayoutManager(new LinearLayoutManager(this));

        swipe.setRefreshing(true);

        requestOpenSubject();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestOpenSubject();
            }
        });
    }

    private void requestOpenSubject(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                openSubjects.getSubjects().clear();
                openSubjects = ParseOpenSubject.getOpenSubjects();
                Message message = new Message();
                message.what = OPEN;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void setOpenSubjects(){
        recycler.setAdapter(new OpenSubjectAdapter(this,openSubjects));
        swipe.setRefreshing(false);
    }
}
