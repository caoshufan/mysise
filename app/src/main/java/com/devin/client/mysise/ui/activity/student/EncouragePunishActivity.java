package com.devin.client.mysise.ui.activity.student;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.Encourages;
import com.devin.client.mysise.model.parse.ParseEncouragePunish;
import com.devin.client.mysise.ui.activity.SwipeBackActivity;
import com.devin.client.mysise.ui.adapter.EncouragePunishAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;


public class EncouragePunishActivity extends SwipeBackActivity {

    private static final int UPDATE_LIST = 1;

    public static Encourages encourages = new Encourages();

    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;
    private SwipeBackLayout swipeBackLayout;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_LIST:
                    setUpdateList();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_encourage_punish;
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

    private void init() {
        setTitle("奖惩表");
        swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        swipe.setRefreshing(true);
        requestList();
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestList();
            }
        });

    }

    private void requestList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                encourages.getEncourages().clear();
                encourages = ParseEncouragePunish.getEncourages();
                Message message = new Message();
                message.what = UPDATE_LIST;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void setUpdateList(){
        recycler.setAdapter(new EncouragePunishAdapter(this, encourages));
        swipe.setRefreshing(false);
    }

}
