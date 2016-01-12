package com.devin.client.mysise.ui.activity.student;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Attendances;
import com.devin.client.mysise.model.parse.ParseAttendence;
import com.devin.client.mysise.ui.activity.SwipeBackActivity;
import com.devin.client.mysise.ui.adapter.AttendanceAdapter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class AttendanceActivity extends SwipeBackActivity {

    private static final int ADDLIST = 1;

    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;
    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    private SwipeBackLayout swipeBackLayout;

    public static Attendances attendances = new Attendances();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ADDLIST:
                    setAddlist();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_attendance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        toolbarLayout.setTitle("考勤表");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadBackdrop();

        swipe.setRefreshing(true);

        requestAttendance();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestAttendance();
            }
        });

    }

    private void requestAttendance() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                attendances.getAttendances().clear();
                attendances = ParseAttendence.getAttendances();
                Message message = new Message();
                message.what = ADDLIST;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void setAddlist() {
        AttendanceAdapter attendanceAdapter = new AttendanceAdapter(this,attendances.getAttendances());
        recycler.setAdapter(attendanceAdapter);
        swipe.setRefreshing(false);
    }

    private void loadBackdrop(){
        Picasso.with(this).load(R.drawable.header_attendance).into(backdrop);
    }
}
