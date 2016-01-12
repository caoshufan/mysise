package com.devin.client.mysise.ui.activity.student;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Exams;
import com.devin.client.mysise.model.parse.ParseExam;
import com.devin.client.mysise.ui.activity.SwipeBackActivity;
import com.devin.client.mysise.ui.adapter.ExamAdapter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExamActivity extends SwipeBackActivity {

    private static final int UPDATA_EXAM = 1;

    public static Exams exams = new Exams();

    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.backdrop)
    ImageView backdrop;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATA_EXAM:
                    setUpdataExam();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_exam;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
        init();
    }

    private void init(){

        toolbarLayout.setTitle("考试表");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadBackdrop();

        recycler.setLayoutManager(new LinearLayoutManager(this));

        swipe.setRefreshing(true);

        requestExam();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestExam();
            }
        });

    }

    private void requestExam(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                exams.getExams().clear();
                exams = ParseExam.getExams();
                Message message = new Message();
                message.what = UPDATA_EXAM;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void setUpdataExam(){
        recycler.setAdapter(new ExamAdapter(this,exams));
        swipe.setRefreshing(false);
    }

    private void loadBackdrop(){
        Picasso.with(this).load(R.drawable.header_exam).into(backdrop);
    }
}
