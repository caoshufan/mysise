package com.devin.client.mysise.ui.activity.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.base.HandleURL;
import com.devin.client.mysise.model.base.StudentLogin;
import com.devin.client.mysise.model.bean.Account;
import com.devin.client.mysise.model.bean.User;
import com.devin.client.mysise.model.parse.ParseUser;
import com.devin.client.mysise.ui.activity.LoginActivity;
import com.devin.client.mysise.ui.adapter.SubjectAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StudentMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;

    private static final int UPDATE_TEXT = 1;

    private User user;

    @Bind(R.id.tech)
    TextView tech;
    @Bind(R.id.insturc)
    TextView insturc;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.id)
    TextView id;
    @Bind(R.id.clas)
    TextView clas;
    @Bind(R.id.email)
    TextView email;
    @Bind(R.id.teacher)
    TextView teacher;
    @Bind(R.id.instructor)
    TextView instructor;
    @Bind(R.id.GPA)
    TextView GPA;
    @Bind(R.id.recycler)
    RecyclerView recycler;

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

    }


    private void init() {
        initView();
        initSise();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initSise() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Account account = new Account();
//                account.setUsername("1340124146");
//                account.setPassword("tyntf");
                SharedPreferences sharedPreferences = getSharedPreferences("account",Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("username", "");
                String password = sharedPreferences.getString("password","");
                account.setUsername(name);
                account.setPassword(password);
                StudentLogin.Instantce(account);
                HandleURL.Instantce();
                user = ParseUser.getUser();
                Message message = new Message();
                message.what = UPDATE_TEXT;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void setUpdateText() {
        tech.setVisibility(View.VISIBLE);
        insturc.setVisibility(View.VISIBLE);
        id.setText(user.getUserid());
        name.setText(user.getName());
        clas.setText(user.getAdministartiveClass());
        email.setText(user.getEmail());
        teacher.setText(user.getHeadTeacher());
        instructor.setText(user.getInstructor());
        GPA.setText(user.getGPA());
        recycler.setAdapter(new SubjectAdapter(user.getSubjects(), this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                break;
            case R.id.action_loginout:
                //TODO 注销，删除sharePreference;
                SharedPreferences sharedPreferences = getSharedPreferences("account",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(StudentMainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_encouragepunish:
                EncouragePunishActivity.encourages.getEncourages().clear();
                startOtherActivity(StudentMainActivity.this, EncouragePunishActivity.class);
                break;
            case R.id.nav_shedular:
                startOtherActivity(StudentMainActivity.this, ScheduleActivity.class);
                break;
            case R.id.nav_attendence:
                AttendanceActivity.attendances.getAttendances().clear();
                startOtherActivity(StudentMainActivity.this, AttendanceActivity.class);
                break;
            case R.id.nav_exam:
                ExamActivity.exams.getExams().clear();
                startOtherActivity(StudentMainActivity.this, ExamActivity.class);
                break;
            case R.id.nav_status:
                StatusActivity.status.getStatus().clear();
                startOtherActivity(StudentMainActivity.this, StatusActivity.class);
                break;
            case R.id.nav_opensubject:
                startOtherActivity(StudentMainActivity.this, OpenSubjectActivity.class);
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startOtherActivity(Context context, Class<?> to) {
        Intent intent = new Intent(context, to);
        startActivity(intent);
        navigationView.setCheckedItem(R.id.nav_userinfo);
    }

    @Override
    /**
     * Bug found in some when toolbar is half-way collapsed and a touch is made on image (some phones only)
     */
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            return false;
        }
    }
}
