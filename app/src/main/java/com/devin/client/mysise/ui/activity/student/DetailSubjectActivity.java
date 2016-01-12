package com.devin.client.mysise.ui.activity.student;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.bean.DetailSubject;
import com.devin.client.mysise.model.parse.ParseDetailSubject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailSubjectActivity extends AppCompatActivity {

    private final static int UPDATE_TEXT = 1;
    @Bind(R.id.sum)
    TextView sum;

    private DetailSubject detailSubject;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.id)
    TextView id;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.department)
    TextView department;
    @Bind(R.id.way)
    TextView way;
    @Bind(R.id.preview)
    TextView preview;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private String URL;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    setUpdateText();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_subject);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        URL = intent.getStringExtra("URL");

        requestDate();
    }

    private void requestDate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                detailSubject = ParseDetailSubject.getDetailSubject(URL);
                Message message = new Message();
                message.what = UPDATE_TEXT;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void setUpdateText() {
        id.setText(detailSubject.getId());
        name.setText(detailSubject.getName());
        department.setText(detailSubject.getDepartment());
        way.setText(detailSubject.getStartSubjectWey());
        preview.setText(detailSubject.getPerview());
        sum.setText(detailSubject.getSummary());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
